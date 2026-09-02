package com.example.data.model

import java.util.UUID

enum class Language(val code: String, val displayName: String, val flag: String, val ttsLocale: String) {
    SPANISH("es", "Spanish", "🇪🇸", "es-ES"),
    FRENCH("fr", "French", "🇫🇷", "fr-FR"),
    GERMAN("de", "German", "🇩🇪", "de-DE"),
    ITALIAN("it", "Italian", "🇮🇹", "it-IT"),
    JAPANESE("ja", "Japanese", "🇯🇵", "ja-JP"),
    ARABIC("ar", "Arabic", "🇲🇦", "ar-SA"),
    PORTUGUESE("pt", "Portuguese", "🇵🇹", "pt-PT"),
    CHINESE("zh", "Chinese", "🇨🇳", "zh-CN"),
    RUSSIAN("ru", "Russian", "🇷🇺", "ru-RU"),
    ENGLISH("en", "English", "🇬🇧", "en-US")
}

enum class TranslationLanguage(val code: String, val displayName: String, val flag: String, val nativeName: String) {
    ARABIC("ar", "Arabic", "🇲🇦", "العربية"),
    FRENCH("fr", "French", "🇫🇷", "Français"),
    ENGLISH("en", "English", "🇬🇧", "English"),
    SPANISH("es", "Spanish", "🇪🇸", "Español"),
    GERMAN("de", "German", "🇩🇪", "Deutsch"),
    ITALIAN("it", "Italian", "🇮🇹", "Italiano"),
    PORTUGUESE("pt", "Portuguese", "🇵🇹", "Português"),
    CHINESE("zh", "Chinese", "🇨🇳", "中文"),
    RUSSIAN("ru", "Russian", "🇷🇺", "Русский"),
    JAPANESE("ja", "Japanese", "🇯🇵", "日本語")
}

enum class DifficultyLevel(val code: String, val label: String, val colorHex: Long) {
    A1("A1", "Beginner", 0xFF10B981),
    A2("A2", "Elementary", 0xFF06B6D4),
    B1("B1", "Intermediate", 0xFF3B82F6),
    B2("B2", "Upper Int.", 0xFF8B5CF6),
    C1("C1", "Advanced", 0xFFF59E0B),
    C2("C2", "Mastery", 0xFFEF4444)
}

enum class ReaderDisplayMode(val label: String, val description: String) {
    PARALLEL("Parallel View", "Target text and translation side-by-side"),
    SUBTITLE("Interlinear", "Translation placed right under target sentence"),
    TAP_TO_REVEAL("Tap to Reveal", "Translation hidden until tapped for active recall"),
    TARGET_ONLY("Target Only", "Pure immersion with tap lookup on individual words")
}

enum class ReaderColorScheme(val label: String, val backgroundHex: Long, val textHex: Long, val translationHex: Long, val cardBgHex: Long) {
    LIGHT("Day", 0xFFF8FAFC, 0xFF0F172A, 0xFF64748B, 0xFFFFFFFF),
    SEPIA("Sepia", 0xFFFBF0D9, 0xFF433422, 0xFF856A54, 0xFFF5E6CA),
    NIGHT("Twilight", 0xFF1E293B, 0xFFF1F5F9, 0xFF94A3B8, 0xFF0F172A),
    DARK("OLED Dark", 0xFF000000, 0xFFE2E8F0, 0xFF64748B, 0xFF121212)
}

enum class BookCoverTheme(
    val id: String,
    val themeName: String,
    val primaryColor: Long,
    val secondaryColor: Long,
    val accentGoldColor: Long,
    val patternType: String
) {
    ROYAL_MIDNIGHT("midnight", "Royal Midnight", 0xFF0F172A, 0xFF1E293B, 0xFFF59E0B, "geometric"),
    NOBLE_EMERALD("emerald", "Noble Emerald", 0xFF064E3B, 0xFF047857, 0xFFFBBF24, "filigree"),
    CRIMSON_RUBY("ruby", "Imperial Crimson", 0xFF7F1D1D, 0xFF991B1B, 0xFFFDE047, "ornate"),
    CELESTIAL_INDIGO("indigo", "Celestial Indigo", 0xFF312E81, 0xFF4338CA, 0xFF38BDF8, "constellation"),
    DESERT_AMBER("amber", "Desert Gold", 0xFF78350F, 0xFFB45309, 0xFFFDE68A, "moroccan"),
    VINTAGE_LEATHER("leather", "Vintage Leather", 0xFF3E2723, 0xFF4E342E, 0xFFD4AF37, "classic")
}

data class BilingualParagraph(
    val id: String = UUID.randomUUID().toString(),
    val targetText: String,
    val translationText: String,
    val notes: String = ""
)

data class BookPage(
    val pageNumber: Int,
    val totalPages: Int,
    val paragraphs: List<BilingualParagraph>
) {
    val fullTargetText: String get() = paragraphs.joinToString("\n\n") { it.targetText }
    val fullTranslationText: String get() = paragraphs.joinToString("\n\n") { it.translationText }
}

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val translatedTitle: String,
    val author: String,
    val description: String,
    val targetLanguage: Language,
    val nativeLanguage: String = "English",
    val difficulty: DifficultyLevel,
    val coverEmoji: String = "📖",
    val coverGradientStart: Long = 0xFF4F46E5,
    val coverGradientEnd: Long = 0xFF06B6D4,
    val coverTheme: BookCoverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
    val paragraphs: List<BilingualParagraph>,
    val isAiGenerated: Boolean = false,
    val isFavorite: Boolean = false,
    val currentParagraphIndex: Int = 0,
    val currentPageIndex: Int = 0,
    val totalWords: Int = 0,
    val explicitPageCount: Int = 0
) {
    fun getPages(paragraphsPerPage: Int = 2): List<BookPage> {
        if (paragraphs.isEmpty()) return listOf(BookPage(1, 1, emptyList()))
        val chunks = paragraphs.chunked(paragraphsPerPage.coerceAtLeast(1))
        val total = chunks.size
        return chunks.mapIndexed { index, list ->
            BookPage(
                pageNumber = index + 1,
                totalPages = total,
                paragraphs = list
            )
        }
    }

    val totalPagesCount: Int
        get() = if (explicitPageCount > 0) explicitPageCount else (paragraphs.size / 2).coerceAtLeast(1)
}

data class VocabularyWord(
    val id: String = UUID.randomUUID().toString(),
    val word: String,
    val translation: String,
    val phonetic: String = "",
    val partOfSpeech: String = "",
    val exampleTarget: String = "",
    val exampleTranslation: String = "",
    val languageCode: String,
    val masteryLevel: Int = 0, // 0 to 5
    val nextReviewTimestamp: Long = System.currentTimeMillis(),
    val bookTitleSource: String = "Reader"
)
