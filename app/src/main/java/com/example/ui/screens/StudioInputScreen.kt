package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProjectInput
import com.example.ui.theme.*

@Composable
fun StudioInputScreen(
    input: ProjectInput,
    isGenerating: Boolean,
    progressMessage: String,
    onInputChange: ((ProjectInput) -> ProjectInput) -> Unit,
    onGenerateClick: () -> Unit
) {
    val videoTypes = listOf(
        "Romantic Drama",
        "Psychological Horror",
        "Viral Comedy Reel",
        "High-Octane Action",
        "Sci-Fi Suspense",
        "Devotional / Epic",
        "Commercial / Ad"
    )

    val aspectRatios = listOf("9:16 Vertical", "16:9 Landscape", "1:1 Square")
    val durations = listOf("15 seconds", "30 seconds", "60 seconds", "3 minutes")
    val aiTools = listOf("Google Veo", "OpenAI Sora", "Kling AI", "Runway Gen-3", "Luma Dream Machine")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CineBackgroundDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // Banner Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(CineSurfaceDark, CineSurfaceVariant)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, CineGold.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Videocam,
                            contentDescription = null,
                            tint = CineGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "UNIVERSAL AI FILM DIRECTOR",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = CineGold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Human-Like Acting + Professional Cinematography + Script Analysis",
                        fontSize = 12.sp,
                        color = CineTextSecondary
                    )
                }
            }
        }

        // Generating Overlay Banner
        if (isGenerating) {
            item {
                CinematicGeneratingBanner(progressMessage = progressMessage)
            }
        }

        // Topic / Idea
        item {
            OutlinedTextField(
                value = input.topic,
                onValueChange = { newVal -> onInputChange { it.copy(topic = newVal) } },
                label = { Text("Topic / Film Idea (विषय)", color = CineGold) },
                placeholder = { Text("e.g. An old retired cop confronts his younger self during an interrogation...", color = CineTextMuted) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_topic"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CineGold,
                    unfocusedBorderColor = CineBorder,
                    focusedTextColor = CineTextPrimary,
                    unfocusedTextColor = CineTextPrimary,
                    focusedContainerColor = CineSurfaceDark,
                    unfocusedContainerColor = CineSurfaceDark
                ),
                shape = RoundedCornerShape(10.dp),
                maxLines = 3
            )
        }

        // Script / Dialogue (Optional or Paste)
        item {
            OutlinedTextField(
                value = input.script,
                onValueChange = { newVal -> onInputChange { it.copy(script = newVal) } },
                label = { Text("Script / Dialogues (कहानी / संवाद)", color = CineGold) },
                placeholder = { Text("Paste existing script or dialogue lines with character cues...", color = CineTextMuted) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_script"),
                minLines = 3,
                maxLines = 6,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CineGold,
                    unfocusedBorderColor = CineBorder,
                    focusedTextColor = CineTextPrimary,
                    unfocusedTextColor = CineTextPrimary,
                    focusedContainerColor = CineSurfaceDark,
                    unfocusedContainerColor = CineSurfaceDark
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }

        // Genre / Video Type
        item {
            Text("Video Genre (शैली)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = CineTextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(videoTypes) { type ->
                    val isSelected = input.videoType == type
                    FilterChip(
                        selected = isSelected,
                        onClick = { onInputChange { it.copy(videoType = type) } },
                        label = { Text(type, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CineGold,
                            selectedLabelColor = Color(0xFF0F172A),
                            containerColor = CineSurfaceDark,
                            labelColor = CineTextSecondary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = if (isSelected) CineGold else CineBorder
                        )
                    )
                }
            }
        }

        // Target AI Tool
        item {
            Text("Target AI Video Tool (AI टूल)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = CineTextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(aiTools) { tool ->
                    val isSelected = input.targetAiTool.contains(tool.split(" ").last())
                    FilterChip(
                        selected = isSelected,
                        onClick = { onInputChange { it.copy(targetAiTool = tool) } },
                        label = { Text(tool, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CineCyan,
                            selectedLabelColor = Color(0xFF0F172A),
                            containerColor = CineSurfaceDark,
                            labelColor = CineTextSecondary
                        )
                    )
                }
            }
        }

        // Format & Duration Row
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // Aspect Ratio
                Column(modifier = Modifier.weight(1f)) {
                    Text("Aspect Ratio", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CineTextPrimary)
                    Spacer(modifier = Modifier.height(6.dp))
                    aspectRatios.forEach { ratio ->
                        val isSelected = input.aspectRatio == ratio
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) CineSurfaceVariant else Color.Transparent)
                                .clickable { onInputChange { it.copy(aspectRatio = ratio) } }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { onInputChange { it.copy(aspectRatio = ratio) } },
                                colors = RadioButtonDefaults.colors(selectedColor = CineGold)
                            )
                            Text(ratio, fontSize = 12.sp, color = if (isSelected) CineGold else CineTextSecondary)
                        }
                    }
                }

                // Duration
                Column(modifier = Modifier.weight(1f)) {
                    Text("Duration", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = CineTextPrimary)
                    Spacer(modifier = Modifier.height(6.dp))
                    durations.forEach { dur ->
                        val isSelected = input.duration == dur
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) CineSurfaceVariant else Color.Transparent)
                                .clickable { onInputChange { it.copy(duration = dur) } }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { onInputChange { it.copy(duration = dur) } },
                                colors = RadioButtonDefaults.colors(selectedColor = CineCyan)
                            )
                            Text(dur, fontSize = 12.sp, color = if (isSelected) CineCyan else CineTextSecondary)
                        }
                    }
                }
            }
        }

        // Language & Target Audience
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = input.language,
                    onValueChange = { newVal -> onInputChange { it.copy(language = newVal) } },
                    label = { Text("Language", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CineGold,
                        unfocusedBorderColor = CineBorder,
                        focusedTextColor = CineTextPrimary,
                        unfocusedTextColor = CineTextPrimary,
                        focusedContainerColor = CineSurfaceDark,
                        unfocusedContainerColor = CineSurfaceDark
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = input.targetAudience,
                    onValueChange = { newVal -> onInputChange { it.copy(targetAudience = newVal) } },
                    label = { Text("Audience", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CineGold,
                        unfocusedBorderColor = CineBorder,
                        focusedTextColor = CineTextPrimary,
                        unfocusedTextColor = CineTextPrimary,
                        focusedContainerColor = CineSurfaceDark,
                        unfocusedContainerColor = CineSurfaceDark
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        // Characters & Location hints
        item {
            OutlinedTextField(
                value = input.charactersHint,
                onValueChange = { newVal -> onInputChange { it.copy(charactersHint = newVal) } },
                label = { Text("Characters (पात्र)", fontSize = 12.sp) },
                placeholder = { Text("e.g. Aryan (25, anxious poet), Zoya (24, fearless artist)") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CineGold,
                    unfocusedBorderColor = CineBorder,
                    focusedTextColor = CineTextPrimary,
                    unfocusedTextColor = CineTextPrimary,
                    focusedContainerColor = CineSurfaceDark,
                    unfocusedContainerColor = CineSurfaceDark
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        item {
            OutlinedTextField(
                value = input.locationHint,
                onValueChange = { newVal -> onInputChange { it.copy(locationHint = newVal) } },
                label = { Text("Location & Atmosphere (स्थान व माहौल)", fontSize = 12.sp) },
                placeholder = { Text("e.g. Rooftop terrace in Varanasi during twilight with river breeze") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CineGold,
                    unfocusedBorderColor = CineBorder,
                    focusedTextColor = CineTextPrimary,
                    unfocusedTextColor = CineTextPrimary,
                    focusedContainerColor = CineSurfaceDark,
                    unfocusedContainerColor = CineSurfaceDark
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Big Master Action Button
        item {
            Button(
                onClick = onGenerateClick,
                enabled = !isGenerating,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("direct_film_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CineGold,
                    contentColor = Color(0xFF0F172A),
                    disabledContainerColor = CineSurfaceVariant,
                    disabledContentColor = CineTextMuted
                )
            ) {
                if (isGenerating) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color(0xFF0F172A),
                        strokeWidth = 2.5.dp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "DIRECTING MASTERPIECE...",
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.MovieFilter,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "DIRECT FILM PRODUCTION (11 PHASES)",
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CinematicGeneratingBanner(progressMessage: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(CineGoldDark.copy(alpha = 0.3f), CineSurfaceElevated)
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, CineGold.copy(alpha = alpha), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(CineRed, RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "DIRECTOR AT WORK — ROLL CAMERA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = CineGold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = progressMessage,
                    fontSize = 12.sp,
                    color = CineTextPrimary
                )
            }
        }
    }
}
