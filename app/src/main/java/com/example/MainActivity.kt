package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.ui.screens.CreateStoryScreen
import com.example.ui.screens.ImportFileSheet
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.ReaderScreen
import com.example.ui.screens.VocabularyScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.BookViewModel

enum class AppDestination(val title: String) {
    LIBRARY("Stories"),
    STUDIO("AI Studio"),
    VOCABULARY("Word Bank"),
    READER("Reader")
}

class MainActivity : ComponentActivity() {

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppContent(viewModel = bookViewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: BookViewModel) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestination.LIBRARY) }
    var showImportSheet by remember { mutableStateOf(false) }

    // Intercept hardware back button when inside Reader
    BackHandler(enabled = currentDestination == AppDestination.READER) {
        viewModel.stopSpeaking()
        currentDestination = AppDestination.LIBRARY
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = currentDestination != AppDestination.READER,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.testTag("main_bottom_nav")
                ) {
                    NavigationBarItem(
                        selected = currentDestination == AppDestination.LIBRARY,
                        onClick = { currentDestination = AppDestination.LIBRARY },
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Filled.MenuBook,
                                contentDescription = "Stories Platform"
                            )
                        },
                        label = { Text("Stories", fontWeight = FontWeight.SemiBold) },
                        modifier = Modifier.testTag("nav_item_library")
                    )

                    NavigationBarItem(
                        selected = currentDestination == AppDestination.STUDIO,
                        onClick = { currentDestination = AppDestination.STUDIO },
                        icon = {
                            Icon(
                                Icons.Default.AutoAwesome,
                                contentDescription = "AI Story Studio"
                            )
                        },
                        label = { Text("AI Studio", fontWeight = FontWeight.SemiBold) },
                        modifier = Modifier.testTag("nav_item_studio")
                    )

                    NavigationBarItem(
                        selected = currentDestination == AppDestination.VOCABULARY,
                        onClick = { currentDestination = AppDestination.VOCABULARY },
                        icon = {
                            Icon(
                                Icons.Default.School,
                                contentDescription = "Word Bank & Flashcards"
                            )
                        },
                        label = { Text("Word Bank", fontWeight = FontWeight.SemiBold) },
                        modifier = Modifier.testTag("nav_item_vocab")
                    )
                }
            }
        }
    ) { innerPadding ->
        when (currentDestination) {
            AppDestination.LIBRARY -> {
                LibraryScreen(
                    viewModel = viewModel,
                    onOpenBook = {
                        currentDestination = AppDestination.READER
                    },
                    onNavigateToCreate = {
                        currentDestination = AppDestination.STUDIO
                    },
                    onOpenImport = {
                        showImportSheet = true
                    }
                )
            }
            AppDestination.STUDIO -> {
                CreateStoryScreen(
                    viewModel = viewModel,
                    onStoryCreated = {
                        currentDestination = AppDestination.READER
                    }
                )
            }
            AppDestination.VOCABULARY -> {
                VocabularyScreen(
                    viewModel = viewModel
                )
            }
            AppDestination.READER -> {
                ReaderScreen(
                    viewModel = viewModel,
                    onBack = {
                        viewModel.stopSpeaking()
                        currentDestination = AppDestination.LIBRARY
                    }
                )
            }
        }

        if (showImportSheet) {
            ImportFileSheet(
                viewModel = viewModel,
                onBookImported = {
                    showImportSheet = false
                    currentDestination = AppDestination.READER
                },
                onDismiss = {
                    showImportSheet = false
                }
            )
        }
    }
}
