package com.example.model

data class ProjectInput(
    val topic: String = "",
    val script: String = "",
    val videoType: String = "Romantic Drama",
    val language: String = "Hindi & English",
    val targetAudience: String = "Young Adults & Cinema Enthusiasts",
    val duration: String = "30 seconds",
    val aspectRatio: String = "9:16 Vertical",
    val visualStyle: String = "Photorealistic Cinematic 35mm",
    val charactersHint: String = "Two college sweethearts meeting after 5 years",
    val locationHint: String = "Old cafe in Mumbai during heavy monsoon rain",
    val targetAiTool: String = "Google Veo / Sora",
    val budgetLevel: String = "High Production Indie"
)

data class ProjectBrief(
    val title: String,
    val objective: String,
    val genre: String,
    val targetAudience: String,
    val durationAndRatio: String,
    val visualStyle: String,
    val loglineSummary: String
)

data class StoryStructure(
    val beginning: String,
    val middle: String,
    val climax: String,
    val resolution: String
)

data class EmotionalPoint(
    val timeLabel: String,
    val tensionPercent: Int, // 0 to 100
    val emotion: String,
    val description: String
)

data class CharacterPsychology(
    val characterName: String,
    val personality: String,
    val motivation: String,
    val coreFear: String,
    val coreDesire: String,
    val internalState: String
)

data class SubtextEntry(
    val character: String,
    val spokenDialogue: String,
    val literalMeaning: String,
    val hiddenSubtext: String
)

data class ScriptIntelligence(
    val storyStructure: StoryStructure,
    val emotionalGraph: List<EmotionalPoint>,
    val characterPsychology: List<CharacterPsychology>,
    val subtextAnalysis: List<SubtextEntry>,
    val turningPoints: List<String>,
    val scriptImprovements: List<String>
)

data class CharacterBibleEntry(
    val characterId: String,
    val name: String,
    val visualAge: String,
    val facialFeatures: String,
    val hairAndMakeup: String,
    val bodyPosture: String,
    val costumeDetails: String,
    val voicePattern: String,
    val actingPattern: String,
    val aiReferencePrompt: String
)

data class EnvironmentBibleEntry(
    val locationName: String,
    val environmentType: String,
    val architecturalDetails: String,
    val propsAndFurniture: String,
    val atmosphericMood: String,
    val aiEnvironmentPrompt: String
)

data class DirectorShot(
    val shotId: String,
    val sceneId: String,
    val timeCode: String,
    val durationSec: Int,
    val shotType: String, // Extreme Close-up, Medium Close-up, Wide, etc.
    val cameraAngle: String, // Eye-level, Low angle, Dutch angle, POV
    val cameraMovement: String, // Slow push-in, Handheld micro-drift, Static
    val lensSuggestion: String, // 50mm f/1.4, 85mm Anamorphic, 28mm Wide
    val composition: String, // Headroom, Rule of thirds, negative space
    val charactersPresent: String,
    val characterPosition: String,
    val facialExpression: String,
    val bodyLanguage: String,
    val dialogue: String,
    val voiceDirection: String,
    val lighting: String,
    val background: String,
    val colorGrade: String,
    val soundDesign: String,
    val transition: String,
    val continuityNotes: String,
    val aiVideoPrompt: String,
    val avoidList: String
)

data class ActingDirection(
    val eyeMovementAndBlink: String,
    val facialMicroExpressions: String,
    val postureAndTension: String,
    val handGestures: String,
    val pauseAndBreathing: String,
    val reactionTiming: String,
    val genreNuance: String
)

data class ScenePlan(
    val sceneNumber: String,
    val sceneTitle: String,
    val narrativePurpose: String,
    val emotionalTone: String,
    val keyLighting: String,
    val cameraStyle: String,
    val actingFocus: String
)

data class DialogueSequenceStep(
    val stepLabel: String,
    val characterAction: String,
    val emotionalSubtext: String,
    val cameraCoverage: String
)

data class EditingSoundPlan(
    val rhythmPacing: String,
    val dialogueSyncRule: String,
    val ambientSound: String,
    val foleyProps: String,
    val musicalScoreCues: List<String>,
    val criticalSilenceMoment: String,
    val transitionPacing: String
)

data class QualityCheckItem(
    val category: String, // Acting, Cinematography, Continuity, Story
    val checkpoint: String,
    val passStatus: Boolean,
    val notes: String
)

data class ProductionPackage(
    val id: String,
    val input: ProjectInput,
    val brief: ProjectBrief,
    val scriptIntelligence: ScriptIntelligence,
    val characterBibles: List<CharacterBibleEntry>,
    val environmentBibles: List<EnvironmentBibleEntry>,
    val scenePlans: List<ScenePlan>,
    val shotList: List<DirectorShot>,
    val dialogueReactionSteps: List<DialogueSequenceStep>,
    val editingSoundPlan: EditingSoundPlan,
    val qualityChecklist: List<QualityCheckItem>,
    val fullMasterExport: String
)
