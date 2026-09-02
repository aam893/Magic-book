package com.example.data.repository

import android.content.Context
import com.example.data.api.GeminiService
import com.example.data.local.AppDatabase
import com.example.data.local.BookEntity
import com.example.data.local.VocabularyEntity
import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.VocabularyWord
import com.example.data.sample.SampleBooks
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookRepository(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val bookDao = database.bookDao()
    private val vocabularyDao = database.vocabularyDao()

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val paragraphListType = Types.newParameterizedType(List::class.java, BilingualParagraph::class.java)
    private val paragraphAdapter = moshi.adapter<List<BilingualParagraph>>(paragraphListType)

    init {
        // Seed default books asynchronously if database is empty
        CoroutineScope(Dispatchers.IO).launch {
            seedSampleBooksIfEmpty()
        }
    }

    val allBooks: Flow<List<Book>> = bookDao.getAllBooks().map { entities ->
        entities.map { entityToBook(it) }
    }

    val allVocabulary: Flow<List<VocabularyWord>> = vocabularyDao.getAllVocabulary().map { entities ->
        entities.map { entityToVocabulary(it) }
    }

    private suspend fun seedSampleBooksIfEmpty() = withContext(Dispatchers.IO) {
        val sampleEntities = SampleBooks.list.map { bookToEntity(it) }
        bookDao.insertBooks(sampleEntities)

        // Seed some initial vocabulary words
        val initialVocab = listOf(
            VocabularyEntity(
                id = "vocab-1",
                word = "el cordero",
                translation = "the lamb / sheep",
                phonetic = "/koɾˈde.ɾo/",
                partOfSpeech = "noun (m)",
                exampleTarget = "Por favor, dibújame un cordero.",
                exampleTranslation = "Please, draw me a sheep.",
                languageCode = "es",
                masteryLevel = 2,
                nextReviewTimestamp = System.currentTimeMillis(),
                bookTitleSource = "El Principito"
            ),
            VocabularyEntity(
                id = "vocab-2",
                word = "le coucher du soleil",
                translation = "the sunset",
                phonetic = "/ku.ʃe dy sɔ.lɛj/",
                partOfSpeech = "phrase",
                exampleTarget = "Rendez-vous au phare au coucher du soleil.",
                exampleTranslation = "Meet at the lighthouse at sunset.",
                languageCode = "fr",
                masteryLevel = 1,
                nextReviewTimestamp = System.currentTimeMillis(),
                bookTitleSource = "Le Voyage Mystérieux"
            ),
            VocabularyEntity(
                id = "vocab-3",
                word = "das Geheimnis",
                translation = "the secret / mystery",
                phonetic = "/ɡəˈhaɪ̯mnɪs/",
                partOfSpeech = "noun (n)",
                exampleTarget = "Die Legende birgt ein uraltes Geheimnis.",
                exampleTranslation = "The legend holds an ancient secret.",
                languageCode = "de",
                masteryLevel = 3,
                nextReviewTimestamp = System.currentTimeMillis(),
                bookTitleSource = "Die Legende vom Zauberwald"
            )
        )
        vocabularyDao.insertWords(initialVocab)
    }

    suspend fun saveBook(book: Book) = withContext(Dispatchers.IO) {
        bookDao.insertBook(bookToEntity(book))
    }

    suspend fun updateReadingProgress(bookId: String, paragraphIndex: Int) = withContext(Dispatchers.IO) {
        bookDao.updateReadingProgress(bookId, paragraphIndex)
    }

    suspend fun toggleFavorite(bookId: String, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        bookDao.toggleFavorite(bookId, isFavorite)
    }

    suspend fun deleteBook(bookId: String) = withContext(Dispatchers.IO) {
        bookDao.deleteBookById(bookId)
    }

    suspend fun saveWord(word: VocabularyWord) = withContext(Dispatchers.IO) {
        val entity = VocabularyEntity(
            id = word.id,
            word = word.word,
            translation = word.translation,
            phonetic = word.phonetic,
            partOfSpeech = word.partOfSpeech,
            exampleTarget = word.exampleTarget,
            exampleTranslation = word.exampleTranslation,
            languageCode = word.languageCode,
            masteryLevel = word.masteryLevel,
            nextReviewTimestamp = word.nextReviewTimestamp,
            bookTitleSource = word.bookTitleSource
        )
        vocabularyDao.insertWord(entity)
    }

    suspend fun updateWordMastery(wordId: String, newLevel: Int) = withContext(Dispatchers.IO) {
        val nextReview = System.currentTimeMillis() + when (newLevel) {
            1 -> 24 * 60 * 60 * 1000L // 1 day
            2 -> 3 * 24 * 60 * 60 * 1000L // 3 days
            3 -> 7 * 24 * 60 * 60 * 1000L // 1 week
            4 -> 14 * 24 * 60 * 60 * 1000L // 2 weeks
            5 -> 30 * 24 * 60 * 60 * 1000L // 1 month
            else -> 12 * 60 * 60 * 1000L
        }
        vocabularyDao.updateWordMastery(wordId, newLevel, nextReview)
    }

    suspend fun deleteWord(wordId: String) = withContext(Dispatchers.IO) {
        vocabularyDao.deleteWordById(wordId)
    }

    suspend fun generateStoryWithAI(
        topic: String,
        targetLanguage: Language,
        translationLanguage: com.example.data.model.TranslationLanguage,
        difficulty: DifficultyLevel,
        genre: String,
        pageCount: Int = 1
    ): Result<Book> {
        val result = GeminiService.generateBilingualStory(topic, targetLanguage, translationLanguage, difficulty, genre, pageCount)
        if (result.isSuccess) {
            val book = result.getOrNull()
            if (book != null) {
                saveBook(book)
            }
        }
        return result
    }

    suspend fun translateParagraphs(
        paragraphs: List<String>,
        targetLanguage: Language,
        translationLanguage: com.example.data.model.TranslationLanguage
    ): Result<List<BilingualParagraph>> {
        return GeminiService.translateParagraphsBatch(paragraphs, targetLanguage, translationLanguage)
    }

    suspend fun lookupWordOnline(
        word: String,
        sentence: String,
        targetLanguage: Language,
        translationLanguage: com.example.data.model.TranslationLanguage
    ): Result<VocabularyWord> {
        return GeminiService.lookupWord(word, sentence, targetLanguage, translationLanguage)
    }

    private fun bookToEntity(book: Book): BookEntity {
        return BookEntity(
            id = book.id,
            title = book.title,
            translatedTitle = book.translatedTitle,
            author = book.author,
            description = book.description,
            targetLanguageCode = book.targetLanguage.code,
            nativeLanguage = book.nativeLanguage,
            difficultyCode = book.difficulty.code,
            coverEmoji = book.coverEmoji,
            coverGradientStart = book.coverGradientStart,
            coverGradientEnd = book.coverGradientEnd,
            paragraphsJson = paragraphAdapter.toJson(book.paragraphs),
            isAiGenerated = book.isAiGenerated,
            isFavorite = book.isFavorite,
            currentParagraphIndex = book.currentParagraphIndex,
            totalWords = book.totalWords
        )
    }

    private fun entityToBook(entity: BookEntity): Book {
        val language = Language.values().firstOrNull { it.code == entity.targetLanguageCode } ?: Language.SPANISH
        val difficulty = DifficultyLevel.values().firstOrNull { it.code == entity.difficultyCode } ?: DifficultyLevel.A1
        val paragraphs = try {
            paragraphAdapter.fromJson(entity.paragraphsJson) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }

        val theme = when {
            entity.coverGradientStart == 0xFF064E3B.toLong() || entity.coverGradientStart == 0xFF10B981.toLong() -> com.example.data.model.BookCoverTheme.NOBLE_EMERALD
            entity.coverGradientStart == 0xFF7F1D1D.toLong() || entity.coverGradientStart == 0xFFF43F5E.toLong() -> com.example.data.model.BookCoverTheme.CRIMSON_RUBY
            entity.coverGradientStart == 0xFF78350F.toLong() || entity.coverGradientStart == 0xFFD97706.toLong() || entity.coverGradientStart == 0xFFD4AF37.toLong() -> com.example.data.model.BookCoverTheme.DESERT_AMBER
            entity.coverGradientStart == 0xFF312E81.toLong() || entity.coverGradientStart == 0xFF06B6D4.toLong() -> com.example.data.model.BookCoverTheme.CELESTIAL_INDIGO
            entity.coverGradientStart == 0xFF3E2723.toLong() -> com.example.data.model.BookCoverTheme.VINTAGE_LEATHER
            else -> com.example.data.model.BookCoverTheme.ROYAL_MIDNIGHT
        }

        return Book(
            id = entity.id,
            title = entity.title,
            translatedTitle = entity.translatedTitle,
            author = entity.author,
            description = entity.description,
            targetLanguage = language,
            nativeLanguage = entity.nativeLanguage,
            difficulty = difficulty,
            coverEmoji = entity.coverEmoji,
            coverGradientStart = entity.coverGradientStart,
            coverGradientEnd = entity.coverGradientEnd,
            coverTheme = theme,
            paragraphs = paragraphs,
            isAiGenerated = entity.isAiGenerated,
            isFavorite = entity.isFavorite,
            currentParagraphIndex = entity.currentParagraphIndex,
            totalWords = entity.totalWords
        )
    }

    private fun entityToVocabulary(entity: VocabularyEntity): VocabularyWord {
        return VocabularyWord(
            id = entity.id,
            word = entity.word,
            translation = entity.translation,
            phonetic = entity.phonetic,
            partOfSpeech = entity.partOfSpeech,
            exampleTarget = entity.exampleTarget,
            exampleTranslation = entity.exampleTranslation,
            languageCode = entity.languageCode,
            masteryLevel = entity.masteryLevel,
            nextReviewTimestamp = entity.nextReviewTimestamp,
            bookTitleSource = entity.bookTitleSource
        )
    }
}
