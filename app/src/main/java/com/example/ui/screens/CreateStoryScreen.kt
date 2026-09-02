package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.TranslationLanguage
import com.example.ui.components.BookCoverCanvas
import com.example.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreateStoryScreen(
    viewModel: BookViewModel,
    onStoryCreated: () -> Unit
) {
    val studioState by viewModel.studioState.collectAsState()
    val defaultTransLang by viewModel.preferredTranslationLanguage.collectAsState()

    var selectedLanguage by remember { mutableStateOf(Language.FRENCH) }
    var selectedTranslationLanguage by remember(defaultTransLang) { mutableStateOf(defaultTransLang) }
    var selectedDifficulty by remember { mutableStateOf(DifficultyLevel.A2) }
    var selectedGenre by remember { mutableStateOf("Adventure") }
    var selectedPageCount by remember { mutableStateOf(5) }
    var selectedCoverTheme by remember { mutableStateOf(BookCoverTheme.ROYAL_MIDNIGHT) }
    var topicPrompt by remember { mutableStateOf("") }

    val pageOptions = listOf(
        Pair(1, "1 Page (Quick)"),
        Pair(3, "3 Pages"),
        Pair(5, "5 Pages (Standard)"),
        Pair(10, "10 Pages (Chapter)"),
        Pair(15, "15 Pages (Novella)"),
        Pair(20, "20 Pages (Extended)"),
        Pair(30, "30 Pages (Full Novel)")
    )

    val genres = listOf("Adventure", "Daily Life", "Mystery", "Folklore", "Sci-Fi", "Comedy")
    val promptSuggestions = listOf(
        "A detective in Paris solving an ancient museum mystery",
        "Two travelers exploring the historical souks and secrets of Marrakech",
        "A friendly barista in Barcelona discovering a legendary coffee recipe",
        "A celestial explorer searching for rare glowing plants in deep space",
        "A painter in Florence discovering an enchanted talking portrait"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AI Story Studio",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .padding(bottom = 96.dp)
        ) {
            // Live Professional Canvas Book Cover Preview
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "LIVE CANVAS COVER PREVIEW",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    val previewTitle = if (topicPrompt.isNotBlank()) topicPrompt.take(35) else "$selectedGenre in ${selectedLanguage.displayName}"
                    val previewTrans = "Translated to ${selectedTranslationLanguage.displayName}"

                    BookCoverCanvas(
                        title = previewTitle,
                        translatedTitle = previewTrans,
                        author = "AI Studio (${selectedDifficulty.code})",
                        targetLanguage = selectedLanguage,
                        difficulty = selectedDifficulty,
                        theme = selectedCoverTheme,
                        pageCount = selectedPageCount,
                        modifier = Modifier
                            .width(170.dp)
                            .padding(vertical = 4.dp),
                        elevation = 10.dp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "✨ Generated with Gold Foil Canvas & $selectedPageCount Distinct Pages",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 1. Target Language to learn
            Text(
                text = "1. Target Language to Learn / Read",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Language.values().forEach { lang ->
                    FilterChip(
                        selected = selectedLanguage == lang,
                        onClick = { selectedLanguage = lang },
                        label = { Text("${lang.flag} ${lang.displayName}") },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 2. Translation / Native Language
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Translate,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "2. Translate To (Your Native Language)",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TranslationLanguage.values().forEach { transLang ->
                    FilterChip(
                        selected = selectedTranslationLanguage == transLang,
                        onClick = {
                            selectedTranslationLanguage = transLang
                            viewModel.setTranslationLanguage(transLang)
                        },
                        label = { Text("${transLang.flag} ${transLang.displayName} (${transLang.nativeName})") },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Number of Pages / Story Length (up to 30 Pages)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Description,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "3. Story Length: Pages (Up to 30 Pages)",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pageOptions.forEach { (count, label) ->
                    FilterChip(
                        selected = selectedPageCount == count,
                        onClick = { selectedPageCount = count },
                        label = { Text(label) },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                            selectedLabelColor = MaterialTheme.colorScheme.onTertiary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 4. Canvas Cover Style
            Text(
                text = "4. Artistic Canvas Cover Style",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BookCoverTheme.values().forEach { theme ->
                    FilterChip(
                        selected = selectedCoverTheme == theme,
                        onClick = { selectedCoverTheme = theme },
                        label = { Text(theme.themeName) },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(theme.primaryColor),
                            selectedLabelColor = Color(theme.accentGoldColor)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 5. CEFR Difficulty Level
            Text(
                text = "5. CEFR Proficiency Level",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DifficultyLevel.values().forEach { level ->
                    FilterChip(
                        selected = selectedDifficulty == level,
                        onClick = { selectedDifficulty = level },
                        label = {
                            Text("${level.code} - ${level.label}")
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(level.colorHex),
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 6. Genre
            Text(
                text = "6. Story Genre",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                genres.forEach { genre ->
                    FilterChip(
                        selected = selectedGenre == genre,
                        onClick = { selectedGenre = genre },
                        label = { Text(genre) },
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 7. Custom Topic or Idea
            Text(
                text = "7. Story Topic or Plot Idea",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = topicPrompt,
                onValueChange = { topicPrompt = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("story_prompt_input"),
                placeholder = { Text("e.g., A traveler discovering a hidden library in the desert...") },
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Inspiration Prompts:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))

            promptSuggestions.forEach { suggestion ->
                Surface(
                    onClick = { topicPrompt = suggestion },
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = suggestion,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Generate Button
            Button(
                onClick = {
                    val prompt = if (topicPrompt.isNotBlank()) topicPrompt else "$selectedGenre in ${selectedLanguage.displayName}"
                    viewModel.generateStory(
                        topic = prompt,
                        targetLanguage = selectedLanguage,
                        translationLanguage = selectedTranslationLanguage,
                        difficulty = selectedDifficulty,
                        genre = selectedGenre,
                        pageCount = selectedPageCount,
                        onSuccess = {
                            onStoryCreated()
                        }
                    )
                },
                enabled = !studioState.isGenerating,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("generate_story_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                if (studioState.isGenerating) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Generating $selectedPageCount Pages with Gemini...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                } else {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "Create $selectedPageCount-Page Story",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            if (studioState.error != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Notice: ${studioState.error}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
