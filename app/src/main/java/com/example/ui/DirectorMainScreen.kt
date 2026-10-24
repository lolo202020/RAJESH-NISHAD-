package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.DirectorTopBar
import com.example.ui.screens.*
import com.example.ui.theme.*

@Composable
fun DirectorMainScreen(
    viewModel: DirectorViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val currentProject by viewModel.currentProject.collectAsStateWithLifecycle()
    val inputState by viewModel.inputState.collectAsStateWithLifecycle()
    val isGenerating by viewModel.isGenerating.collectAsStateWithLifecycle()
    val progressMessage by viewModel.generationProgress.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val shotViewMode by viewModel.shotViewMode.collectAsStateWithLifecycle()
    val selectedFilter by viewModel.selectedShotFilter.collectAsStateWithLifecycle()
    val toastMsg by viewModel.toastMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(toastMsg) {
        toastMsg?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearToast()
        }
    }

    // Handle back button: if not on Studio Desk, go back to Studio Desk
    BackHandler(enabled = selectedTab != DirectorTab.STUDIO) {
        viewModel.selectTab(DirectorTab.STUDIO)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CineBackgroundDark,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            DirectorTopBar(
                currentTitle = currentProject.brief.title,
                onLoadPreset = { preset -> viewModel.loadPreset(preset) },
                onNewProjectClick = { viewModel.selectTab(DirectorTab.STUDIO) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = CineSurfaceDark,
                contentColor = CineTextPrimary,
                tonalElevation = 8.dp,
                windowInsets = WindowInsets.navigationBars,
                modifier = Modifier.testTag("bottom_nav_bar")
            ) {
                DirectorTab.values().forEach { tab ->
                    val isSelected = selectedTab == tab
                    val icon = when (tab) {
                        DirectorTab.STUDIO -> Icons.Default.Edit
                        DirectorTab.ACTING -> Icons.Default.Psychology
                        DirectorTab.SHOTS -> Icons.Default.Videocam
                        DirectorTab.BIBLE -> Icons.Default.Lightbulb
                        DirectorTab.VAULT -> Icons.Default.Storage
                        DirectorTab.EXPORT -> Icons.Default.Checklist
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.selectTab(tab) },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = tab.label
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0F172A),
                            selectedTextColor = CineGold,
                            indicatorColor = CineGold,
                            unselectedIconColor = CineTextSecondary,
                            unselectedTextColor = CineTextSecondary
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                DirectorTab.STUDIO -> {
                    StudioInputScreen(
                        input = inputState,
                        isGenerating = isGenerating,
                        progressMessage = progressMessage,
                        onInputChange = { modifier -> viewModel.updateInput(modifier) },
                        onGenerateClick = { viewModel.generateFilmProduction() }
                    )
                }
                DirectorTab.ACTING -> {
                    ActingSimulationScreen(production = currentProject)
                }
                DirectorTab.SHOTS -> {
                    ShotListScreen(
                        production = currentProject,
                        viewMode = shotViewMode,
                        selectedFilter = selectedFilter,
                        onToggleViewMode = { viewModel.toggleShotViewMode() },
                        onFilterChange = { filter -> viewModel.setShotFilter(filter) },
                        onCopyPrompt = { ctx, text, label -> viewModel.copyToClipboard(ctx, text, label) },
                        onEnhancePrompt = { shotId -> viewModel.enhanceShotPromptWithAi(shotId) }
                    )
                }
                DirectorTab.BIBLE -> {
                    LightingAndBibleScreen(
                        production = currentProject,
                        onCopyText = { ctx, text, label -> viewModel.copyToClipboard(ctx, text, label) }
                    )
                }
                DirectorTab.VAULT -> {
                    CharacterVaultScreen()
                }
                DirectorTab.EXPORT -> {
                    ExportScreen(
                        production = currentProject,
                        onCopyAllPrompts = { ctx -> viewModel.copyAllAiPrompts(ctx) },
                        onCopyCsv = { ctx, label ->
                            viewModel.copyToClipboard(ctx, viewModel.generateCsvExport(), label)
                        },
                        onCopyFullPackage = { ctx, fullText ->
                            viewModel.copyToClipboard(ctx, fullText, "Master Film Production Bible")
                        },
                        onExportGallery = { ctx -> viewModel.exportMasterVideoTimelineToGallery(ctx) }
                    )
                }
            }
        }
    }
}
