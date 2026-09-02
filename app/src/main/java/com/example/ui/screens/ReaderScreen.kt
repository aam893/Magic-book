package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BilingualParagraph
import com.example.data.model.BookPage
import com.example.data.model.ReaderColorScheme
import com.example.data.model.ReaderDisplayMode
import com.example.ui.components.AudioPlayerBar
import com.example.ui.components.BookCoverCanvas
import com.example.ui.viewmodel.BookViewModel
import com.example.ui.viewmodel.ReaderViewType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ReaderScreen(
    viewModel: BookViewModel,
    onBack: () -> Unit
) {
    val readerState by viewModel.readerState.collectAsState()
    val book = readerState.currentBook

    var showMenu by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    if (book == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No book selected")
        }
        return
    }

    val pages = remember(book) { book.getPages() }
    val totalPages = pages.size.coerceAtLeast(1)

    val pagerState = rememberPagerState(
        initialPage = readerState.currentPageIndex.coerceIn(0, totalPages - 1),
        pageCount = { totalPages }
    )

    // Sync Pager state with ViewModel currentPageIndex when updated externally
    LaunchedEffect(readerState.currentPageIndex) {
        if (pagerState.currentPage != readerState.currentPageIndex && readerState.currentPageIndex in 0 until totalPages) {
            pagerState.animateScrollToPage(readerState.currentPageIndex)
        }
    }

    // Sync ViewModel when user swipes pager
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page != readerState.currentPageIndex) {
                viewModel.setPage(page)
            }
        }
    }

    val currentTheme = readerState.colorScheme
    val bgColor = Color(currentTheme.backgroundHex)
    val textColor = Color(currentTheme.textHex)
    val translationColor = Color(currentTheme.translationHex)
    val cardBgColor = Color(currentTheme.cardBgHex)

    Scaffold(
        containerColor = bgColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Page ${readerState.currentPageIndex + 1} of $totalPages • ${book.targetLanguage.displayName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = translationColor
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("reader_back_button")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textColor
                        )
                    }
                },
                actions = {
                    // Translation Language Quick Switcher
                    var transMenuExpanded by remember { mutableStateOf(false) }
                    Box {
                        Surface(
                            onClick = { transMenuExpanded = true },
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${readerState.translationLanguage.flag} ${readerState.translationLanguage.displayName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = transMenuExpanded,
                            onDismissRequest = { transMenuExpanded = false }
                        ) {
                            Text(
                                "Translate Words Into:",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            com.example.data.model.TranslationLanguage.values().forEach { lang ->
                                DropdownMenuItem(
                                    text = { Text("${lang.flag} ${lang.displayName} (${lang.nativeName})") },
                                    onClick = {
                                        viewModel.setTranslationLanguage(lang)
                                        transMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Font Size -
                    IconButton(onClick = { viewModel.changeFontSize(-2f) }) {
                        Text("A-", fontWeight = FontWeight.Bold, color = textColor, fontSize = 13.sp)
                    }
                    // Font Size +
                    IconButton(onClick = { viewModel.changeFontSize(2f) }) {
                        Text("A+", fontWeight = FontWeight.Bold, color = textColor, fontSize = 15.sp)
                    }

                    // Mode & Settings menu
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Reader Settings", tint = textColor)
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            Text(
                                "View Layout",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            ReaderViewType.values().forEach { vType ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            vType.label,
                                            fontWeight = if (readerState.viewType == vType) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    onClick = {
                                        viewModel.setViewType(vType)
                                        showMenu = false
                                    }
                                )
                            }

                            Text(
                                "Display Mode",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            ReaderDisplayMode.values().forEach { mode ->
                                DropdownMenuItem(
                                    text = {
                                        Column {
                                            Text(
                                                mode.label,
                                                fontWeight = if (readerState.displayMode == mode) FontWeight.Bold else FontWeight.Normal
                                            )
                                            Text(
                                                mode.description,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    },
                                    onClick = {
                                        viewModel.setDisplayMode(mode)
                                        showMenu = false
                                    }
                                )
                            }

                            Text(
                                "Canvas Theme",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            ReaderColorScheme.values().forEach { scheme ->
                                DropdownMenuItem(
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clip(CircleShape)
                                                    .background(Color(scheme.backgroundHex))
                                                    .border(1.dp, Color.Gray, CircleShape)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(scheme.label)
                                        }
                                    },
                                    onClick = {
                                        viewModel.setColorScheme(scheme)
                                        showMenu = false
                                    }
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = bgColor
                )
            )
        },
        bottomBar = {
            // Audio TTS Pro Player with Speed Controls & Waveform
            AudioPlayerBar(
                isPlaying = readerState.isPlayingAudio,
                currentPage = readerState.currentPageIndex + 1,
                totalPages = totalPages,
                speechRate = readerState.speechRate,
                targetLanguageName = book.targetLanguage.displayName,
                onTogglePlayPause = { viewModel.togglePlayPause() },
                onPreviousPage = { viewModel.previousPage() },
                onNextPage = { viewModel.nextPage() },
                onReplayCurrentPage = { viewModel.replayCurrentPage() },
                onSetSpeechRate = { speed -> viewModel.setSpeechRate(speed) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(bgColor)
        ) {
            when (readerState.viewType) {
                ReaderViewType.PAGES -> {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("book_pages_pager")
                    ) { pageIndex ->
                        val page = pages.getOrElse(pageIndex) {
                            BookPage(pageNumber = pageIndex + 1, totalPages = totalPages, paragraphs = emptyList())
                        }

                        SinglePageView(
                            page = page,
                            book = book,
                            readerState = readerState,
                            textColor = textColor,
                            translationColor = translationColor,
                            cardBgColor = cardBgColor,
                            onWordClick = { word, contextSentence ->
                                viewModel.lookupWord(word, contextSentence, book.targetLanguage)
                            },
                            onParagraphClick = { pIndex ->
                                viewModel.setParagraphIndex(pIndex)
                            },
                            onToggleReveal = { pIndex ->
                                viewModel.toggleRevealTranslation(pIndex)
                            },
                            onSpeakParagraph = { pIndex, text ->
                                viewModel.speakParagraph(pIndex, text, book.targetLanguage)
                            }
                        )
                    }
                }
                ReaderViewType.SCROLL -> {
                    ContinuousScrollView(
                        book = book,
                        readerState = readerState,
                        textColor = textColor,
                        translationColor = translationColor,
                        cardBgColor = cardBgColor,
                        onWordClick = { word, contextSentence ->
                            viewModel.lookupWord(word, contextSentence, book.targetLanguage)
                        },
                        onParagraphClick = { pIndex ->
                            viewModel.setParagraphIndex(pIndex)
                        },
                        onToggleReveal = { pIndex ->
                            viewModel.toggleRevealTranslation(pIndex)
                        },
                        onSpeakParagraph = { pIndex, text ->
                            viewModel.speakParagraph(pIndex, text, book.targetLanguage)
                        }
                    )
                }
            }
        }

        // Word Lookup Modal
        if (readerState.selectedWordLookup != null || readerState.isLookingUpWord) {
            WordLookupBottomSheet(
                wordLookup = readerState.selectedWordLookup,
                isLoading = readerState.isLookingUpWord,
                translationLanguage = readerState.translationLanguage,
                onSaveToVocab = { word ->
                    viewModel.saveWordToVocabulary(word)
                },
                onPlayAudio = { text ->
                    viewModel.speakParagraph(
                        readerState.currentParagraphIndex,
                        text,
                        book.targetLanguage
                    )
                },
                onDismiss = {
                    viewModel.dismissWordLookup()
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun SinglePageView(
    page: BookPage,
    book: com.example.data.model.Book,
    readerState: com.example.ui.viewmodel.ReaderUiState,
    textColor: Color,
    translationColor: Color,
    cardBgColor: Color,
    onWordClick: (String, String) -> Unit,
    onParagraphClick: (Int) -> Unit,
    onToggleReveal: (Int) -> Unit,
    onSpeakParagraph: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .padding(bottom = 16.dp)
    ) {
        // Page 1: Hero Canvas Book Cover Preview
        if (page.pageNumber == 1) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                BookCoverCanvas(
                    title = book.title,
                    translatedTitle = book.translatedTitle,
                    author = book.author,
                    targetLanguage = book.targetLanguage,
                    difficulty = book.difficulty,
                    theme = book.coverTheme,
                    pageCount = book.totalPagesCount,
                    modifier = Modifier.width(180.dp),
                    elevation = 8.dp
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // Header indicator of page
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
            ) {
                Text(
                    text = "PAGE ${page.pageNumber} OF ${page.totalPages}",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.sp
                )
            }

            Text(
                text = "${book.difficulty.code} • ${book.targetLanguage.displayName}",
                style = MaterialTheme.typography.labelSmall,
                color = translationColor,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Render the page's paragraphs
        page.paragraphs.forEachIndexed { pLocalIndex, paragraph ->
            val globalPIndex = (page.pageNumber - 1) * 2 + pLocalIndex
            val isSelected = globalPIndex == readerState.currentParagraphIndex
            val isSpeaking = readerState.isPlayingAudio && readerState.speakingParagraphIndex == globalPIndex
            val isRevealed = readerState.isRevealedMap[globalPIndex] ?: false

            ParagraphViewCard(
                index = globalPIndex,
                paragraph = paragraph,
                fontSizeSp = readerState.fontSizeSp,
                displayMode = readerState.displayMode,
                isSelected = isSelected,
                isSpeaking = isSpeaking,
                isRevealed = isRevealed,
                textColor = textColor,
                translationColor = translationColor,
                cardBgColor = cardBgColor,
                onSelectParagraph = { onParagraphClick(globalPIndex) },
                onToggleReveal = { onToggleReveal(globalPIndex) },
                onWordClick = { word -> onWordClick(word, paragraph.targetText) },
                onSpeak = { onSpeakParagraph(globalPIndex, paragraph.targetText) }
            )

            Spacer(modifier = Modifier.height(14.dp))
        }

        // Bottom Page Footnote
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "— Page ${page.pageNumber} / ${page.totalPages} —",
                style = MaterialTheme.typography.labelSmall,
                color = translationColor.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun ParagraphViewCard(
    index: Int,
    paragraph: BilingualParagraph,
    fontSizeSp: Float,
    displayMode: ReaderDisplayMode,
    isSelected: Boolean,
    isSpeaking: Boolean,
    isRevealed: Boolean,
    textColor: Color,
    translationColor: Color,
    cardBgColor: Color,
    onSelectParagraph: () -> Unit,
    onToggleReveal: () -> Unit,
    onWordClick: (String) -> Unit,
    onSpeak: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onSelectParagraph() }
            .then(
                if (isSelected || isSpeaking) {
                    Modifier.border(
                        width = 2.dp,
                        color = if (isSpeaking) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(18.dp)
                    )
                } else Modifier
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) cardBgColor.copy(alpha = 0.95f) else cardBgColor.copy(alpha = 0.7f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Paragraph header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "§${index + 1}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else translationColor
                    )
                    if (isSpeaking) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "🔊 Speaking...",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                IconButton(
                    onClick = onSpeak,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Speak paragraph",
                        tint = if (isSpeaking) MaterialTheme.colorScheme.secondary else translationColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Tokenized Interactive Words in Target Language
            val words = remember(paragraph.targetText) {
                paragraph.targetText.split(" ")
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                words.forEach { word ->
                    Text(
                        text = word,
                        fontSize = fontSizeSp.sp,
                        lineHeight = (fontSizeSp * 1.45f).sp,
                        fontWeight = FontWeight.Medium,
                        color = textColor,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .combinedClickable(
                                onClick = { onWordClick(word) }
                            )
                            .padding(horizontal = 2.dp, vertical = 1.dp)
                    )
                }
            }

            // Translation Display Mode
            when (displayMode) {
                ReaderDisplayMode.PARALLEL, ReaderDisplayMode.SUBTITLE -> {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = paragraph.translationText,
                        fontSize = (fontSizeSp * 0.88f).sp,
                        lineHeight = (fontSizeSp * 1.35f).sp,
                        fontStyle = FontStyle.Italic,
                        color = translationColor
                    )
                }
                ReaderDisplayMode.TAP_TO_REVEAL -> {
                    Spacer(modifier = Modifier.height(12.dp))
                    if (isRevealed) {
                        Text(
                            text = paragraph.translationText,
                            fontSize = (fontSizeSp * 0.88f).sp,
                            lineHeight = (fontSizeSp * 1.35f).sp,
                            fontStyle = FontStyle.Italic,
                            color = translationColor
                        )
                    } else {
                        Surface(
                            onClick = onToggleReveal,
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Default.Visibility,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    "Tap to reveal translation",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
                ReaderDisplayMode.TARGET_ONLY -> {
                    // Immersive target only
                }
            }
        }
    }
}

@Composable
fun ContinuousScrollView(
    book: com.example.data.model.Book,
    readerState: com.example.ui.viewmodel.ReaderUiState,
    textColor: Color,
    translationColor: Color,
    cardBgColor: Color,
    onWordClick: (String, String) -> Unit,
    onParagraphClick: (Int) -> Unit,
    onToggleReveal: (Int) -> Unit,
    onSpeakParagraph: (Int, String) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .testTag("reader_continuous_scroll_list"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                BookCoverCanvas(
                    title = book.title,
                    translatedTitle = book.translatedTitle,
                    author = book.author,
                    targetLanguage = book.targetLanguage,
                    difficulty = book.difficulty,
                    theme = book.coverTheme,
                    pageCount = book.totalPagesCount,
                    modifier = Modifier.width(170.dp),
                    elevation = 8.dp
                )
            }
        }

        itemsIndexed(book.paragraphs) { index, paragraph ->
            val isSelected = index == readerState.currentParagraphIndex
            val isSpeaking = readerState.isPlayingAudio && readerState.speakingParagraphIndex == index
            val isRevealed = readerState.isRevealedMap[index] ?: false

            ParagraphViewCard(
                index = index,
                paragraph = paragraph,
                fontSizeSp = readerState.fontSizeSp,
                displayMode = readerState.displayMode,
                isSelected = isSelected,
                isSpeaking = isSpeaking,
                isRevealed = isRevealed,
                textColor = textColor,
                translationColor = translationColor,
                cardBgColor = cardBgColor,
                onSelectParagraph = { onParagraphClick(index) },
                onToggleReveal = { onToggleReveal(index) },
                onWordClick = { word -> onWordClick(word, paragraph.targetText) },
                onSpeak = { onSpeakParagraph(index, paragraph.targetText) }
            )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}
