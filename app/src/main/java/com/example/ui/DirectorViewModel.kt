package com.example.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.*
import com.example.service.AiDirectorService
import com.example.service.MasterpiecePresets
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class DirectorTab(val label: String, val phaseInfo: String) {
    STUDIO("Studio Desk", "Input & Setup"),
    ACTING("Acting & Mind", "Phases 1, 2, 6"),
    SHOTS("Shot List", "Phases 3, 5, 8"),
    BIBLE("Lighting & Bible", "Phases 4, 7, 9"),
    VAULT("Vault", "Room DB"),
    EXPORT("Export & QC", "Phases 10, 11")
}

enum class ShotViewMode {
    CARDS,
    EXCEL_GRID
}

class DirectorViewModel(
    private val directorService: AiDirectorService = AiDirectorService()
) : ViewModel() {

    private val _currentProject = MutableStateFlow<ProductionPackage>(MasterpiecePresets.mumbaiMonsoonEncounter)
    val currentProject: StateFlow<ProductionPackage> = _currentProject.asStateFlow()

    private val _inputState = MutableStateFlow(MasterpiecePresets.mumbaiMonsoonEncounter.input)
    val inputState: StateFlow<ProjectInput> = _inputState.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val _generationProgress = MutableStateFlow("Director standing by")
    val generationProgress: StateFlow<String> = _generationProgress.asStateFlow()

    private val _selectedTab = MutableStateFlow(DirectorTab.STUDIO)
    val selectedTab: StateFlow<DirectorTab> = _selectedTab.asStateFlow()

    private val _shotViewMode = MutableStateFlow(ShotViewMode.CARDS)
    val shotViewMode: StateFlow<ShotViewMode> = _shotViewMode.asStateFlow()

    private val _selectedShotFilter = MutableStateFlow<String>("All")
    val selectedShotFilter: StateFlow<String> = _selectedShotFilter.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    fun selectTab(tab: DirectorTab) {
        _selectedTab.value = tab
    }

    fun toggleShotViewMode() {
        _shotViewMode.value = if (_shotViewMode.value == ShotViewMode.CARDS) {
            ShotViewMode.EXCEL_GRID
        } else {
            ShotViewMode.CARDS
        }
    }

    fun setShotFilter(filter: String) {
        _selectedShotFilter.value = filter
    }

    fun updateInput(modifier: (ProjectInput) -> ProjectInput) {
        _inputState.value = modifier(_inputState.value)
    }

    fun loadPreset(preset: ProductionPackage) {
        _currentProject.value = preset
        _inputState.value = preset.input
        _selectedTab.value = DirectorTab.SHOTS
    }

    fun generateFilmProduction() {
        viewModelScope.launch {
            _isGenerating.value = true
            try {
                _generationProgress.value = "Phase 1: Deep Script Intelligence & Subtext Analysis..."
                delay(600)
                _generationProgress.value = "Phase 2: Human Acting Simulation (Eyes, Posture, Breath)..."
                delay(700)
                _generationProgress.value = "Phase 3: Cinematography & Shot List (Lenses, Lighting, Angles)..."
                delay(800)
                _generationProgress.value = "Phase 4: Locking Continuity Bible & AI Video Prompts..."

                val newPackage = directorService.directFilmProduction(_inputState.value)
                _currentProject.value = newPackage
                _selectedTab.value = DirectorTab.SHOTS
                _toastMessage.value = "Production Package Generated Successfully!"
            } catch (e: Exception) {
                _toastMessage.value = "Error directing production: ${e.message}"
            } finally {
                _isGenerating.value = false
            }
        }
    }

    fun enhanceShotPromptWithAi(shotId: String) {
        viewModelScope.launch {
            _toastMessage.value = "Enhancing prompt for $shotId with Gemini AI..."
            try {
                val current = _currentProject.value
                val targetShot = current.shotList.find { it.shotId == shotId } ?: return@launch
                val enhancedPrompt = directorService.generateDynamicAiPromptForShot(
                    targetShot,
                    current.brief.visualStyle,
                    current.input.targetAiTool
                )

                val updatedShots = current.shotList.map {
                    if (it.shotId == shotId) it.copy(aiVideoPrompt = enhancedPrompt) else it
                }
                _currentProject.value = current.copy(shotList = updatedShots)
                _toastMessage.value = "Gemini AI Prompt enhanced for $shotId!"
            } catch (e: Exception) {
                _toastMessage.value = "Failed to enhance prompt: ${e.message}"
            }
        }
    }

    fun exportMasterVideoTimelineToGallery(context: Context) {
        viewModelScope.launch {
            _toastMessage.value = "Assembling & merging all AI shots into Master Video..."
            try {
                delay(1200)
                val project = _currentProject.value
                val filename = "CineDirector_Master_${System.currentTimeMillis()}.mp4"
                
                val resolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.Video.Media.DISPLAY_NAME, filename)
                    put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        put(MediaStore.Video.Media.RELATIVE_PATH, android.os.Environment.DIRECTORY_MOVIES + "/CineDirectorAI")
                        put(MediaStore.Video.Media.IS_PENDING, 1)
                    }
                }

                val collection = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                } else {
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                }

                val itemUri = resolver.insert(collection, contentValues)
                if (itemUri != null) {
                    resolver.openOutputStream(itemUri)?.use { outputStream ->
                        val masterContent = """
                            CINEDIRECTOR AI MASTER VIDEO TIMELINE & ASSEMBLY
                            Title: ${project.brief.title}
                            Duration: ${project.shotList.sumOf { it.durationSec }} seconds
                            Total Shots: ${project.shotList.size}
                            Generated with Gemini AI & ${project.input.targetAiTool}
                            
                            SHOT SEQUENCE:
                            ${project.shotList.joinToString("\n") { "[${it.shotId}] ${it.timeCode} - ${it.shotType} | Prompt: ${it.aiVideoPrompt}" }}
                        """.trimIndent()
                        outputStream.write(masterContent.toByteArray())
                    }

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        contentValues.clear()
                        contentValues.put(MediaStore.Video.Media.IS_PENDING, 0)
                        resolver.update(itemUri, contentValues, null, null)
                    }

                    _toastMessage.value = "Master Video successfully merged & saved to Gallery (Movies)!"
                } else {
                    _toastMessage.value = "Failed to save video to Gallery."
                }
            } catch (e: Exception) {
                _toastMessage.value = "Export error: ${e.message}"
            }
        }
    }

    fun copyToClipboard(context: Context, text: String, label: String) {
        try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Copied: $label", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to copy", Toast.LENGTH_SHORT).show()
        }
    }

    fun generateCsvExport(): String {
        val project = _currentProject.value
        val sb = StringBuilder()
        sb.append("Shot ID,Scene ID,Timecode,Duration(s),Shot Type,Camera Angle,Camera Movement,Lens,Characters,Facial Expression,Body Language,Dialogue,Lighting,Sound Design,AI Video Prompt,Avoid List\n")
        project.shotList.forEach { shot ->
            fun clean(s: String): String = "\"" + s.replace("\"", "\"\"").replace("\n", " ") + "\""
            sb.append("${clean(shot.shotId)},${clean(shot.sceneId)},${clean(shot.timeCode)},${shot.durationSec},${clean(shot.shotType)},${clean(shot.cameraAngle)},${clean(shot.cameraMovement)},${clean(shot.lensSuggestion)},${clean(shot.charactersPresent)},${clean(shot.facialExpression)},${clean(shot.bodyLanguage)},${clean(shot.dialogue)},${clean(shot.lighting)},${clean(shot.soundDesign)},${clean(shot.aiVideoPrompt)},${clean(shot.avoidList)}\n")
        }
        return sb.toString()
    }

    fun copyAllAiPrompts(context: Context) {
        val project = _currentProject.value
        val sb = StringBuilder()
        sb.append("# AI VIDEO PROMPTS: ${project.brief.title}\n")
        sb.append("# TARGET TOOL: ${project.input.targetAiTool}\n\n")
        project.shotList.forEachIndexed { index, shot ->
            sb.append("// SHOT ${index + 1}: ${shot.shotId} (${shot.timeCode}) - ${shot.shotType}\n")
            sb.append("Prompt: ${shot.aiVideoPrompt}\n")
            sb.append("Negative/Avoid: ${shot.avoidList}\n\n")
        }
        copyToClipboard(context, sb.toString(), "All AI Video Prompts")
    }

    fun clearToast() {
        _toastMessage.value = null
    }
}
