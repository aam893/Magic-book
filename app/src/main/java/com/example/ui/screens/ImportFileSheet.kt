package com.example.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.model.BilingualParagraph
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.TranslationLanguage
import com.example.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportFileSheet(
    viewModel: BookViewModel,
    onBookImported: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val importState by viewModel.importState.collectAsState()
    val defaultTransLang by viewModel.preferredTranslationLanguage.collectAsState()

    var selectedTab by remember { mutableIntStateOf(0) } // 0: PDF / File Picker, 1: Paste Text
    var title by remember { mutableStateOf("") }
    var targetLanguage by remember { mutableStateOf(Language.FRENCH) }
    var translationLanguage by remember(defaultTransLang) { mutableStateOf(defaultTransLang) }
    var difficulty by remember { mutableStateOf(DifficultyLevel.B1) }

    var pastedTargetText by remember { mutableStateOf("") }
    var pastedTranslationText by remember { mutableStateOf("") }

    var targetLangDropdownExpanded by remember { mutableStateOf(false) }
    var transLangDropdownExpanded by remember { mutableStateOf(false) }

    // System File Picker for PDF, TXT, EPUB
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.extractDocumentFromUri(uri)
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        modifier = Modifier.testTag("import_file_modal")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 36.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.UploadFile,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Import External PDF & Stories",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Extract PDF, auto-translate & read interactively",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tabs: Pick File vs Manual Text
            PrimaryTabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.clip(RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("📄 Select PDF / File") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("✍️ Paste Text") }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Story Title
            OutlinedTextField(
                value = title.ifBlank { importState.extractedDocument?.fileName?.removeSuffix(".pdf")?.removeSuffix(".txt") ?: "" },
                onValueChange = { title = it },
                label = { Text("Book / Story Title") },
                placeholder = { Text("e.g., Le Petit Prince / Desert Tales") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Language Selection Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Target Language (PDF language)
                ExposedDropdownMenuBox(
                    expanded = targetLangDropdownExpanded,
                    onExpandedChange = { targetLangDropdownExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = "${targetLanguage.flag} ${targetLanguage.displayName}",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Language of PDF") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = targetLangDropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = targetLangDropdownExpanded,
                        onDismissRequest = { targetLangDropdownExpanded = false }
                    ) {
                        Language.values().forEach { lang ->
                            DropdownMenuItem(
                                text = { Text("${lang.flag} ${lang.displayName}") },
                                onClick = {
                                    targetLanguage = lang
                                    targetLangDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Translation Language
                ExposedDropdownMenuBox(
                    expanded = transLangDropdownExpanded,
                    onExpandedChange = { transLangDropdownExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = "${translationLanguage.flag} ${translationLanguage.displayName}",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Translate To") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = transLangDropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = transLangDropdownExpanded,
                        onDismissRequest = { transLangDropdownExpanded = false }
                    ) {
                        TranslationLanguage.values().forEach { lang ->
                            DropdownMenuItem(
                                text = { Text("${lang.flag} ${lang.displayName} (${lang.nativeName})") },
                                onClick = {
                                    translationLanguage = lang
                                    viewModel.setTranslationLanguage(lang)
                                    transLangDropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedTab == 0) {
                // PDF / Document Picker Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (importState.isExtractingFile) {
                            CircularProgressIndicator(modifier = Modifier.size(36.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Parsing PDF text stream...", style = MaterialTheme.typography.bodyMedium)
                        } else if (importState.extractedDocument != null) {
                            val doc = importState.extractedDocument!!
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = doc.fileName,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${doc.paragraphs.size} paragraphs extracted • ~${doc.estimatedWordCount} words",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                OutlinedButton(
                                    onClick = {
                                        filePickerLauncher.launch(arrayOf("application/pdf", "text/plain", "application/epub+zip", "*/*"))
                                    },
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Change")
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Auto-Translate with AI button
                            Button(
                                onClick = {
                                    viewModel.autoTranslateImportedParagraphs(
                                        paragraphs = doc.paragraphs,
                                        targetLanguage = targetLanguage,
                                        translationLanguage = translationLanguage
                                    )
                                },
                                enabled = !importState.isTranslating,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) {
                                if (importState.isTranslating) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Translating to ${translationLanguage.displayName} with Gemini...")
                                } else {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("✨ Auto-Translate to ${translationLanguage.displayName} (${translationLanguage.nativeName})")
                                }
                            }

                            if (importState.translatedParagraphs.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "✓ ${importState.translatedParagraphs.size} paragraphs translated and aligned!",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Icon(
                                Icons.Default.FileOpen,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(44.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Choose any PDF, EPUB, or TXT file from device storage",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    filePickerLauncher.launch(
                                        arrayOf("application/pdf", "text/plain", "application/epub+zip", "text/*", "*/*")
                                    )
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.testTag("choose_pdf_button")
                            ) {
                                Icon(Icons.Default.UploadFile, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Browse PDF / Document")
                            }
                        }
                    }
                }
            } else {
                // Paste Text Section
                OutlinedTextField(
                    value = pastedTargetText,
                    onValueChange = { pastedTargetText = it },
                    label = { Text("Foreign Text (${targetLanguage.displayName})") },
                    placeholder = { Text("Paste article, book chapter or paragraphs here...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    maxLines = 8,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pastedTranslationText,
                    onValueChange = { pastedTranslationText = it },
                    label = { Text("Translation (${translationLanguage.displayName}) [Optional - can auto-translate]") },
                    placeholder = { Text("Leave blank to auto-translate with Gemini AI...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 6,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            if (importState.error != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = importState.error ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Final Save / Add to Library Button
            val hasContent = (selectedTab == 0 && importState.extractedDocument != null) ||
                    (selectedTab == 1 && pastedTargetText.isNotBlank())

            Button(
                onClick = {
                    val finalTitle = title.ifBlank {
                        importState.extractedDocument?.fileName?.removeSuffix(".pdf") ?: "Imported ${targetLanguage.displayName} Story"
                    }

                    val paragraphs: List<BilingualParagraph> = if (selectedTab == 0) {
                        if (importState.translatedParagraphs.isNotEmpty()) {
                            importState.translatedParagraphs
                        } else {
                            val rawParagraphs = importState.extractedDocument?.paragraphs ?: emptyList()
                            rawParagraphs.map { p ->
                                BilingualParagraph(
                                    targetText = p,
                                    translationText = "Tap word for instant translation in ${translationLanguage.displayName}"
                                )
                            }
                        }
                    } else {
                        val targetList = pastedTargetText.split("\n\n").filter { it.isNotBlank() }
                        val transList = pastedTranslationText.split("\n\n").filter { it.isNotBlank() }
                        targetList.mapIndexed { idx, t ->
                            BilingualParagraph(
                                targetText = t.trim(),
                                translationText = transList.getOrNull(idx)?.trim()
                                    ?: "Tap word for instant translation in ${translationLanguage.displayName}"
                            )
                        }
                    }

                    if (paragraphs.isNotEmpty()) {
                        viewModel.importCustomBook(
                            title = finalTitle,
                            targetLanguage = targetLanguage,
                            translationLanguage = translationLanguage,
                            paragraphs = paragraphs,
                            difficulty = difficulty,
                            sourceDescription = "External PDF / Document Reader"
                        )
                        onBookImported()
                    }
                },
                enabled = hasContent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("save_imported_book_button"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.FileOpen, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Add to Library & Open Interactive Reader",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}
