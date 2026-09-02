package com.example.ui.viewmodel

import android.app.Application
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.ReaderColorScheme
import com.example.data.model.ReaderDisplayMode
import com.example.data.model.TranslationLanguage
import com.example.data.model.VocabularyWord
import com.example.data.repository.BookRepository
import com.example.util.DocumentTextExtractor
import com.example.util.ExtractedDocument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

enum class ReaderViewType(val label: String) {
    PAGES("Book Pages Mode"),
    SCROLL("Continuous Scroll")
}

data class ReaderUiState(
    val currentBook: Book? = null,
    val currentParagraphIndex: Int = 0,
    val currentPageIndex: Int = 0,
    val viewType: ReaderViewType = ReaderViewType.PAGES,
    val fontSizeSp: Float = 19f,
    val displayMode: ReaderDisplayMode = ReaderDisplayMode.SUBTITLE,
    val colorScheme: ReaderColorScheme = ReaderColorScheme.LIGHT,
    val isPlayingAudio: Boolean = false,
    val speechRate: Float = 1.0f,
    val speakingParagraphIndex: Int = -1,
    val selectedWordLookup: VocabularyWord? = null,
    val isLookingUpWord: Boolean = false,
    val isRevealedMap: Map<Int, Boolean> = emptyMap(),
    val translationLanguage: TranslationLanguage = TranslationLanguage.ARABIC
) {
    val totalPages: Int
        get() = currentBook?.totalPagesCount ?: 1
}

data class StudioUiState(
    val isGenerating: Boolean = false,
    val error: String? = null,
    val generatedBook: Book? = null,
    val selectedPageCount: Int = 1
)

data class ImportUiState(
    val isExtractingFile: Boolean = false,
    val isTranslating: Boolean = false,
    val error: String? = null,
    val extractedDocument: ExtractedDocument? = null,
    val translatedParagraphs: List<BilingualParagraph> = emptyList()
)

class BookViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {
    private val repository = BookRepository(application)
    private var tts: TextToSpeech? = null
    private var isTtsInitialized = false

    private val _selectedLanguageFilter = MutableStateFlow<Language?>(null)
    val selectedLanguageFilter = _selectedLanguageFilter.asStateFlow()

    private val _preferredTranslationLanguage = MutableStateFlow(TranslationLanguage.ARABIC)
    val preferredTranslationLanguage = _preferredTranslationLanguage.asStateFlow()

    val books: StateFlow<List<Book>> = combine(
        repository.allBooks,
        _selectedLanguageFilter
    ) { allBooks, filter ->
        if (filter == null) allBooks else allBooks.filter { it.targetLanguage == filter }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vocabulary: StateFlow<List<VocabularyWord>> = repository.allVocabulary
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _readerState = MutableStateFlow(ReaderUiState())
    val readerState = _readerState.asStateFlow()

    private val _studioState = MutableStateFlow(StudioUiState())
    val studioState = _studioState.asStateFlow()

    private val _importState = MutableStateFlow(ImportUiState())
    val importState = _importState.asStateFlow()

    init {
        tts = TextToSpeech(application, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsInitialized = true
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _readerState.value = _readerState.value.copy(isPlayingAudio = true)
                }

                override fun onDone(utteranceId: String?) {
                    viewModelScope.launch {
                        handleUtteranceDone(utteranceId)
                    }
                }

                override fun onError(utteranceId: String?) {
                    viewModelScope.launch {
                        _readerState.value = _readerState.value.copy(
                            isPlayingAudio = false,
                            speakingParagraphIndex = -1
                        )
                    }
                }
            })
        }
    }

    private fun handleUtteranceDone(utteranceId: String?) {
        val state = _readerState.value
        // If audio was stopped or book is absent, stop
        if (!state.isPlayingAudio || state.currentBook == null) {
            _readerState.value = state.copy(isPlayingAudio = false, speakingParagraphIndex = -1)
            return
        }

        val book = state.currentBook
        val currentIndex = state.speakingParagraphIndex
        val nextIndex = currentIndex + 1

        if (nextIndex < book.paragraphs.size) {
            val targetPage = (nextIndex / 2).coerceIn(0, (book.totalPagesCount - 1).coerceAtLeast(0))
            // Auto-advance to next paragraph and sync page
            _readerState.value = state.copy(
                currentParagraphIndex = nextIndex,
                currentPageIndex = targetPage,
                speakingParagraphIndex = nextIndex,
                isPlayingAudio = true
            )
            viewModelScope.launch {
                repository.updateReadingProgress(book.id, nextIndex)
            }
            speakParagraphInternal(nextIndex, book.paragraphs[nextIndex].targetText, book.targetLanguage)
        } else {
            // Reached the end of the entire story
            _readerState.value = state.copy(
                isPlayingAudio = false,
                speakingParagraphIndex = -1
            )
        }
    }

    fun setLanguageFilter(language: Language?) {
        _selectedLanguageFilter.value = language
    }

    fun setTranslationLanguage(lang: TranslationLanguage) {
        _preferredTranslationLanguage.value = lang
        _readerState.value = _readerState.value.copy(translationLanguage = lang)
    }

    fun openBook(book: Book) {
        val initialP = book.currentParagraphIndex
        val initialPage = (initialP / 2).coerceIn(0, (book.totalPagesCount - 1).coerceAtLeast(0))
        _readerState.value = _readerState.value.copy(
            currentBook = book,
            currentParagraphIndex = initialP,
            currentPageIndex = initialPage,
            isRevealedMap = emptyMap(),
            isPlayingAudio = false,
            speakingParagraphIndex = -1,
            selectedWordLookup = null,
            translationLanguage = _preferredTranslationLanguage.value
        )
    }

    fun setPage(pageIndex: Int) {
        val book = _readerState.value.currentBook ?: return
        val clampedPage = pageIndex.coerceIn(0, (book.totalPagesCount - 1).coerceAtLeast(0))
        val targetPIndex = (clampedPage * 2).coerceIn(0, (book.paragraphs.size - 1).coerceAtLeast(0))
        val wasPlaying = _readerState.value.isPlayingAudio

        _readerState.value = _readerState.value.copy(
            currentPageIndex = clampedPage,
            currentParagraphIndex = targetPIndex
        )
        viewModelScope.launch {
            repository.updateReadingProgress(book.id, targetPIndex)
        }

        if (wasPlaying) {
            val p = book.paragraphs.getOrNull(targetPIndex)
            if (p != null) {
                speakParagraph(targetPIndex, p.targetText, book.targetLanguage)
            }
        }
    }

    fun nextPage() {
        setPage(_readerState.value.currentPageIndex + 1)
    }

    fun previousPage() {
        setPage(_readerState.value.currentPageIndex - 1)
    }

    fun replayCurrentPage() {
        val book = _readerState.value.currentBook ?: return
        val pageIndex = _readerState.value.currentPageIndex
        val pIndex = (pageIndex * 2).coerceIn(0, (book.paragraphs.size - 1).coerceAtLeast(0))
        val paragraph = book.paragraphs.getOrNull(pIndex) ?: return
        speakParagraph(pIndex, paragraph.targetText, book.targetLanguage)
    }

    fun setSpeechRate(rate: Float) {
        val clamped = rate.coerceIn(0.5f, 2.0f)
        _readerState.value = _readerState.value.copy(speechRate = clamped)
        tts?.setSpeechRate(clamped)
    }

    fun setViewType(viewType: ReaderViewType) {
        _readerState.value = _readerState.value.copy(viewType = viewType)
    }

    fun setParagraphIndex(index: Int) {
        val book = _readerState.value.currentBook ?: return
        val clamped = index.coerceIn(0, (book.paragraphs.size - 1).coerceAtLeast(0))
        val page = (clamped / 2).coerceIn(0, (book.totalPagesCount - 1).coerceAtLeast(0))
        _readerState.value = _readerState.value.copy(
            currentParagraphIndex = clamped,
            currentPageIndex = page
        )
        viewModelScope.launch {
            repository.updateReadingProgress(book.id, clamped)
        }
    }

    fun toggleRevealTranslation(paragraphIndex: Int) {
        val current = _readerState.value.isRevealedMap[paragraphIndex] ?: false
        val newMap = _readerState.value.isRevealedMap.toMutableMap()
        newMap[paragraphIndex] = !current
        _readerState.value = _readerState.value.copy(isRevealedMap = newMap)
    }

    fun changeFontSize(delta: Float) {
        val newSize = (_readerState.value.fontSizeSp + delta).coerceIn(14f, 32f)
        _readerState.value = _readerState.value.copy(fontSizeSp = newSize)
    }

    fun setDisplayMode(mode: ReaderDisplayMode) {
        _readerState.value = _readerState.value.copy(displayMode = mode)
    }

    fun setColorScheme(scheme: ReaderColorScheme) {
        _readerState.value = _readerState.value.copy(colorScheme = scheme)
    }

    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            repository.toggleFavorite(book.id, !book.isFavorite)
        }
    }

    fun deleteBook(bookId: String) {
        viewModelScope.launch {
            repository.deleteBook(bookId)
            if (_readerState.value.currentBook?.id == bookId) {
                _readerState.value = _readerState.value.copy(currentBook = null)
            }
        }
    }

    fun speakParagraph(index: Int, text: String, language: Language) {
        if (!isTtsInitialized || tts == null) return

        if (_readerState.value.isPlayingAudio && _readerState.value.speakingParagraphIndex == index) {
            stopSpeaking()
            return
        }

        val book = _readerState.value.currentBook
        val page = (index / 2).coerceIn(0, ((book?.totalPagesCount ?: 1) - 1).coerceAtLeast(0))
        _readerState.value = _readerState.value.copy(
            isPlayingAudio = true,
            speakingParagraphIndex = index,
            currentParagraphIndex = index,
            currentPageIndex = page
        )
        if (book != null) {
            viewModelScope.launch {
                repository.updateReadingProgress(book.id, index)
            }
        }
        speakParagraphInternal(index, text, language)
    }

    private fun speakParagraphInternal(index: Int, text: String, language: Language) {
        try {
            val locale = Locale.forLanguageTag(language.ttsLocale)
            tts?.language = locale
            tts?.setSpeechRate(_readerState.value.speechRate)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "UTTERANCE_$index")
        } catch (e: Exception) {
            Log.e("BookViewModel", "TTS error", e)
            _readerState.value = _readerState.value.copy(
                isPlayingAudio = false,
                speakingParagraphIndex = -1
            )
        }
    }

    fun togglePlayPause() {
        if (_readerState.value.isPlayingAudio) {
            stopSpeaking()
        } else {
            val book = _readerState.value.currentBook ?: return
            val currentIndex = _readerState.value.currentParagraphIndex.coerceIn(0, (book.paragraphs.size - 1).coerceAtLeast(0))
            val paragraph = book.paragraphs.getOrNull(currentIndex) ?: return
            speakParagraph(currentIndex, paragraph.targetText, book.targetLanguage)
        }
    }

    fun previousParagraph() {
        val book = _readerState.value.currentBook ?: return
        val newIndex = (_readerState.value.currentParagraphIndex - 1).coerceAtLeast(0)
        val wasPlaying = _readerState.value.isPlayingAudio
        setParagraphIndex(newIndex)
        if (wasPlaying) {
            val paragraph = book.paragraphs.getOrNull(newIndex) ?: return
            speakParagraph(newIndex, paragraph.targetText, book.targetLanguage)
        }
    }

    fun nextParagraph() {
        val book = _readerState.value.currentBook ?: return
        val newIndex = (_readerState.value.currentParagraphIndex + 1).coerceAtMost(book.paragraphs.size - 1)
        val wasPlaying = _readerState.value.isPlayingAudio
        setParagraphIndex(newIndex)
        if (wasPlaying) {
            val paragraph = book.paragraphs.getOrNull(newIndex) ?: return
            speakParagraph(newIndex, paragraph.targetText, book.targetLanguage)
        }
    }

    fun stopSpeaking() {
        tts?.stop()
        _readerState.value = _readerState.value.copy(
            isPlayingAudio = false,
            speakingParagraphIndex = -1
        )
    }

    fun lookupWord(word: String, contextSentence: String, language: Language) {
        val cleanWord = word.trim().replace(Regex("[^\\p{L}\\p{Nd}'-]"), "")
        if (cleanWord.isBlank()) return

        _readerState.value = _readerState.value.copy(
            isLookingUpWord = true,
            selectedWordLookup = null
        )

        val transLang = _readerState.value.translationLanguage

        viewModelScope.launch {
            val result = repository.lookupWordOnline(cleanWord, contextSentence, language, transLang)
            val wordEntry = result.getOrNull() ?: VocabularyWord(
                word = cleanWord,
                translation = "Translation in ${transLang.displayName}",
                languageCode = language.code,
                exampleTarget = contextSentence
            )

            _readerState.value = _readerState.value.copy(
                isLookingUpWord = false,
                selectedWordLookup = wordEntry.copy(bookTitleSource = _readerState.value.currentBook?.title ?: "Reader")
            )
        }
    }

    fun dismissWordLookup() {
        _readerState.value = _readerState.value.copy(selectedWordLookup = null, isLookingUpWord = false)
    }

    fun saveWordToVocabulary(word: VocabularyWord) {
        viewModelScope.launch {
            repository.saveWord(word)
            dismissWordLookup()
        }
    }

    fun updateWordMastery(wordId: String, newLevel: Int) {
        viewModelScope.launch {
            repository.updateWordMastery(wordId, newLevel)
        }
    }

    fun deleteVocabularyWord(wordId: String) {
        viewModelScope.launch {
            repository.deleteWord(wordId)
        }
    }

    fun generateStory(
        topic: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage,
        difficulty: DifficultyLevel,
        genre: String,
        pageCount: Int,
        onSuccess: (Book) -> Unit
    ) {
        _studioState.value = _studioState.value.copy(isGenerating = true, error = null, selectedPageCount = pageCount)
        viewModelScope.launch {
            val result = repository.generateStoryWithAI(topic, targetLanguage, translationLanguage, difficulty, genre, pageCount)
            _studioState.value = _studioState.value.copy(isGenerating = false)
            if (result.isSuccess) {
                val book = result.getOrNull()
                if (book != null) {
                    _studioState.value = _studioState.value.copy(generatedBook = book)
                    openBook(book)
                    onSuccess(book)
                }
            } else {
                _studioState.value = _studioState.value.copy(error = result.exceptionOrNull()?.message ?: "Generation failed")
            }
        }
    }

    fun extractDocumentFromUri(uri: Uri) {
        _importState.value = _importState.value.copy(isExtractingFile = true, error = null)
        viewModelScope.launch {
            val result = DocumentTextExtractor.extractTextFromUri(getApplication(), uri)
            if (result.isSuccess) {
                val doc = result.getOrNull()
                _importState.value = _importState.value.copy(
                    isExtractingFile = false,
                    extractedDocument = doc,
                    translatedParagraphs = emptyList()
                )
            } else {
                _importState.value = _importState.value.copy(
                    isExtractingFile = false,
                    error = "Failed to extract file: ${result.exceptionOrNull()?.localizedMessage}"
                )
            }
        }
    }

    fun autoTranslateImportedParagraphs(
        paragraphs: List<String>,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage,
        onSuccess: (List<BilingualParagraph>) -> Unit = {}
    ) {
        if (paragraphs.isEmpty()) return
        _importState.value = _importState.value.copy(isTranslating = true, error = null)
        viewModelScope.launch {
            val result = repository.translateParagraphs(paragraphs, targetLanguage, translationLanguage)
            _importState.value = _importState.value.copy(isTranslating = false)
            if (result.isSuccess) {
                val translatedList = result.getOrNull() ?: emptyList()
                _importState.value = _importState.value.copy(translatedParagraphs = translatedList)
                onSuccess(translatedList)
            } else {
                _importState.value = _importState.value.copy(error = "Translation error: ${result.exceptionOrNull()?.localizedMessage}")
            }
        }
    }

    fun resetImportState() {
        _importState.value = ImportUiState()
    }

    fun importCustomBook(
        title: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage,
        paragraphs: List<BilingualParagraph>,
        difficulty: DifficultyLevel = DifficultyLevel.B1,
        sourceDescription: String = "Imported PDF / Document"
    ) {
        viewModelScope.launch {
            val newBook = Book(
                id = UUID.randomUUID().toString(),
                title = title.ifBlank { "Imported: ${targetLanguage.displayName} Reader" },
                translatedTitle = "Document (${translationLanguage.displayName})",
                author = "PDF Import",
                description = sourceDescription,
                targetLanguage = targetLanguage,
                nativeLanguage = translationLanguage.displayName,
                difficulty = difficulty,
                coverEmoji = "📄",
                coverGradientStart = 0xFF0D9488,
                coverGradientEnd = 0xFF0284C7,
                paragraphs = paragraphs,
                totalWords = paragraphs.sumOf { it.targetText.split("\\s+".toRegex()).size }
            )

            repository.saveBook(newBook)
            openBook(newBook)
            resetImportState()
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
