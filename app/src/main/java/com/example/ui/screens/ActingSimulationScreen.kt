package com.example.ui.screens

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun ActingSimulationScreen(
    production: ProductionPackage
) {
    val intel = production.scriptIntelligence

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
                icon = Icons.Default.Psychology,
                badge = "PHASE 1 & 2",
                title = "HUMAN ACTING & SCRIPT INTELLIGENCE",
                subtitle = "Subtext decoding, emotional tension curve, and physiological performance guidance"
            )
        }

        // Story Structure Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineBorder, Color.Transparent))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "STORY ARC & NARRATIVE PILLARS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CineGold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    StoryPillarRow("Beginning", intel.storyStructure.beginning, CineCyan)
                    Spacer(modifier = Modifier.height(8.dp))
                    StoryPillarRow("Middle Conflict", intel.storyStructure.middle, CineGold)
                    Spacer(modifier = Modifier.height(8.dp))
                    StoryPillarRow("Climactic Reveal", intel.storyStructure.climax, CineRed)
                    Spacer(modifier = Modifier.height(8.dp))
                    StoryPillarRow("Resolution", intel.storyStructure.resolution, CinePurple)
                }
            }
        }

        // Emotional Graph / Tension Curve Visualization
        item {
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
                        Text(
                            text = "EMOTIONAL TENSION GRAPH (TIME vs INTENSITY)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineGold,
                            letterSpacing = 1.sp
                        )
                        Icon(Icons.Default.ShowChart, contentDescription = null, tint = CineGold, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    intel.emotionalGraph.forEach { point ->
                        EmotionalBarItem(point)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        // Character Psychology & Internal Motivation Cards
        item {
            Text(
                text = "CHARACTER PSYCHOLOGY & HIDDEN MOTIVATION",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CineTextPrimary
            )
        }

        items(intel.characterPsychology) { charPsych ->
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .background(CineGold.copy(alpha = 0.2f), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = CineGold, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(charPsych.characterName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = CineTextPrimary)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    DetailRow("Core Motivation:", charPsych.motivation)
                    DetailRow("Greatest Fear:", charPsych.coreFear)
                    DetailRow("Deepest Desire:", charPsych.coreDesire)
                    DetailRow("Internal State:", charPsych.internalState)
                }
            }
        }

        // Dialogue Subtext Decoder (What they say vs What they feel)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineCyan.copy(alpha = 0.3f), Color.Transparent))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.RecordVoiceOver, contentDescription = null, tint = CineCyan, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SUBTEXT DECODER: SPOKEN WORDS vs HIDDEN TRUTH",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineCyan,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    intel.subtextAnalysis.forEach { sub ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CineSurfaceVariant, RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "${sub.character}: \"${sub.spokenDialogue}\"",
                                fontWeight = FontWeight.Bold,
                                color = CineTextPrimary,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Literal Meaning: ${sub.literalMeaning}",
                                fontSize = 11.sp,
                                color = CineTextSecondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.Top) {
                                Text("True Subtext: ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CineGold)
                                Text(sub.hiddenSubtext, fontSize = 12.sp, color = CineTextPrimary)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        // Dialogue-Reaction Performance Blocking (Phase 6)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "PERFORMANCE BLOCKING: ACTION & REACTION SEQUENCING",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CineGold,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    production.dialogueReactionSteps.forEach { step ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .padding(top = 4.dp)
                                    .background(CineCyan, RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(step.stepLabel, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = CineCyan)
                                Text(step.characterAction, fontSize = 12.sp, color = CineTextPrimary)
                                Text("Emotional intent: ${step.emotionalSubtext}", fontSize = 11.sp, color = CineTextSecondary)
                                Text("Camera: ${step.cameraCoverage}", fontSize = 11.sp, color = CineGoldLight)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DirectorSectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badge: String,
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(listOf(CineSurfaceDark, CineSurfaceVariant)),
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, CineBorder, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(CineGold.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(badge, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CineGold)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(icon, contentDescription = null, tint = CineGold, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, fontWeight = FontWeight.Black, fontSize = 14.sp, color = CineTextPrimary, letterSpacing = 0.5.sp)
            Text(subtitle, fontSize = 12.sp, color = CineTextSecondary)
        }
    }
}

@Composable
fun StoryPillarRow(label: String, content: String, dotColor: Color) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .padding(top = 5.dp)
                .size(8.dp)
                .background(dotColor, RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(label, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = dotColor)
            Text(content, fontSize = 12.sp, color = CineTextPrimary)
        }
    }
}

@Composable
fun EmotionalBarItem(point: EmotionalPoint) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("${point.timeLabel} • ${point.emotion}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = CineTextPrimary)
            Text("${point.tensionPercent}% Tension", fontSize = 11.sp, color = CineGoldLight)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(CineSurfaceVariant)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = (point.tensionPercent / 100f).coerceIn(0.05f, 1f))
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(CineCyan, if (point.tensionPercent > 70) CineRed else CineGold)
                        )
                    )
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(point.description, fontSize = 11.sp, color = CineTextSecondary)
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = CineTextSecondary, modifier = Modifier.width(110.dp))
        Text(value, fontSize = 12.sp, color = CineTextPrimary, modifier = Modifier.weight(1f))
    }
}
