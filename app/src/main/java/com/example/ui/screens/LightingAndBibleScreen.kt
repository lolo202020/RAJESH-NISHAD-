package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterBibleEntry
import com.example.model.EnvironmentBibleEntry
import com.example.model.ProductionPackage
import com.example.ui.theme.*

@Composable
fun LightingAndBibleScreen(
    production: ProductionPackage,
    onCopyText: (Context, String, String) -> Unit
) {
    val context = LocalContext.current
    val soundPlan = production.editingSoundPlan

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CineBackgroundDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // Section Header
        item {
            DirectorSectionHeader(
                icon = Icons.Default.Lightbulb,
                badge = "PHASE 4, 5 & 7",
                title = "LIGHTING, CONTINUITY BIBLE & SOUND",
                subtitle = "Permanent character consistency, environment locks, and cinematic sound design"
            )
        }

        // Lighting Architecture Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineGoldDark, Color.Transparent))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.WbIncandescent, contentDescription = null, tint = CineGold, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CINEMATIC LIGHTING PLAN",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineGold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    DetailRow("Key Light:", "Directional soft source (3200K Tungsten or 5600K Daylight per scene)")
                    DetailRow("Fill Light:", "Subtle negative fill to preserve 3:1 chiaroscuro contrast ratio")
                    DetailRow("Backlight / Rim:", "Hair rim light for separation from dark backgrounds")
                    DetailRow("Practical Lights:", "Table lamps, neon signage, and window light sources")
                    DetailRow("Shadow Quality:", "Soft gradient penumbra across cheekbones; zero harsh digital shadows")
                    DetailRow("Color Palette:", "${production.brief.visualStyle} color grade with controlled saturation")
                }
            }
        }

        // Character Bibles Header
        item {
            Text(
                text = "PERMANENT CHARACTER CONTINUITY BIBLES",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CineTextPrimary
            )
        }

        items(production.characterBibles) { char ->
            CharacterBibleCard(char = char, onCopy = { text, label -> onCopyText(context, text, label) })
        }

        // Environment Bible Header
        item {
            Text(
                text = "ENVIRONMENT & PROPS CONTINUITY BIBLE",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CineTextPrimary
            )
        }

        items(production.environmentBibles) { env ->
            EnvironmentBibleCard(env = env, onCopy = { text, label -> onCopyText(context, text, label) })
        }

        // Sound Design & Editing Pacing
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.GraphicEq, contentDescription = null, tint = CineCyan, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SOUND DESIGN & RHYTHMIC PACING",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineCyan,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    DetailRow("Pacing & Rhythm:", soundPlan.rhythmPacing)
                    DetailRow("Dialogue Sync Rule:", soundPlan.dialogueSyncRule)
                    DetailRow("Ambient Layers:", soundPlan.ambientSound)
                    DetailRow("Foley & Props:", soundPlan.foleyProps)
                    DetailRow("Silence Moment:", soundPlan.criticalSilenceMoment)

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Musical Score Cues:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CineTextSecondary)
                    soundPlan.musicalScoreCues.forEach { cue ->
                        Text("• $cue", fontSize = 11.sp, color = CineTextPrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterBibleCard(
    char: CharacterBibleEntry,
    onCopy: (String, String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineBorder, Color.Transparent))),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(char.name, fontSize = 15.sp, fontWeight = FontWeight.Black, color = CineGold)
                    Text(char.visualAge, fontSize = 11.sp, color = CineTextSecondary)
                }

                Button(
                    onClick = { onCopy(char.aiReferencePrompt, "Character Ref: ${char.name}") },
                    colors = ButtonDefaults.buttonColors(containerColor = CineGoldDark, contentColor = CineGoldLight),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    modifier = Modifier.height(28.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Copy Ref Prompt", fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            DetailRow("Facial Features:", char.facialFeatures)
            DetailRow("Hair & Makeup:", char.hairAndMakeup)
            DetailRow("Body Posture:", char.bodyPosture)
            DetailRow("Costume Locked:", char.costumeDetails)
            DetailRow("Voice Pattern:", char.voicePattern)
            DetailRow("Acting Habit:", char.actingPattern)

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CineBackgroundDark, RoundedCornerShape(6.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = "AI Consistent Ref: \"${char.aiReferencePrompt}\"",
                    fontSize = 11.sp,
                    color = CineCyan
                )
            }
        }
    }
}

@Composable
fun EnvironmentBibleCard(
    env: EnvironmentBibleEntry,
    onCopy: (String, String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(env.locationName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CineTextPrimary)
                    Text(env.environmentType, fontSize = 11.sp, color = CineCyan)
                }

                Button(
                    onClick = { onCopy(env.aiEnvironmentPrompt, "Env Ref: ${env.locationName}") },
                    colors = ButtonDefaults.buttonColors(containerColor = CineSurfaceVariant, contentColor = CineCyan),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    modifier = Modifier.height(28.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Copy Env Prompt", fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            DetailRow("Architecture:", env.architecturalDetails)
            DetailRow("Key Props:", env.propsAndFurniture)
            DetailRow("Atmosphere:", env.atmosphericMood)
        }
    }
}
