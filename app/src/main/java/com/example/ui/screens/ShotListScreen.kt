package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DirectorShot
import com.example.model.ProductionPackage
import com.example.ui.ShotViewMode
import com.example.ui.theme.*

@Composable
fun ShotListScreen(
    production: ProductionPackage,
    viewMode: ShotViewMode,
    selectedFilter: String,
    onToggleViewMode: () -> Unit,
    onFilterChange: (String) -> Unit,
    onCopyPrompt: (Context, String, String) -> Unit,
    onEnhancePrompt: (String) -> Unit
) {
    val context = LocalContext.current
    val allShots = production.shotList

    val filteredShots = remember(allShots, selectedFilter) {
        when (selectedFilter) {
            "Close-Ups" -> allShots.filter { it.shotType.contains("Close-Up", ignoreCase = true) }
            "Medium" -> allShots.filter { it.shotType.contains("Medium", ignoreCase = true) }
            "Wide" -> allShots.filter { it.shotType.contains("Wide", ignoreCase = true) }
            "Reaction" -> allShots.filter { it.shotType.contains("Reaction", ignoreCase = true) || it.shotType.contains("Reverse", ignoreCase = true) }
            else -> allShots
        }
    }

    val filterOptions = listOf("All (${allShots.size})", "Close-Ups", "Medium", "Wide", "Reaction")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CineBackgroundDark)
    ) {
        // Top Controls: Mode Switch & Filter Chips
        Surface(
            color = CineSurfaceDark,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "DIRECTOR'S SHOT LIST",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = CineGold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "${filteredShots.size} Shots • Total ${allShots.sumOf { it.durationSec }}s • ${production.input.targetAiTool}",
                            fontSize = 11.sp,
                            color = CineTextSecondary
                        )
                    }

                    FilledTonalButton(
                        onClick = onToggleViewMode,
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = CineSurfaceVariant,
                            contentColor = CineCyan
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("toggle_view_mode_button")
                    ) {
                        Icon(
                            imageVector = if (viewMode == ShotViewMode.CARDS) Icons.Default.TableChart else Icons.Default.ViewAgenda,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (viewMode == ShotViewMode.CARDS) "Excel Sheet View" else "Card View",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Filters
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(filterOptions) { opt ->
                        val cleanOpt = opt.split(" ").first()
                        val isSelected = selectedFilter == cleanOpt || (cleanOpt == "All" && selectedFilter == "All")
                        FilterChip(
                            selected = isSelected,
                            onClick = { onFilterChange(cleanOpt) },
                            label = { Text(opt, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CineGoldDark,
                                selectedLabelColor = CineGoldLight,
                                containerColor = CineSurfaceVariant,
                                labelColor = CineTextSecondary
                            )
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = CineBorder, thickness = 1.dp)

        // Main List Content
        if (viewMode == ShotViewMode.CARDS) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
            ) {
                items(filteredShots, key = { it.shotId }) { shot ->
                    DirectorShotCard(
                        shot = shot,
                        onCopyPrompt = { p, l -> onCopyPrompt(context, p, l) },
                        onEnhancePrompt = { onEnhancePrompt(shot.shotId) }
                    )
                }
            }
        } else {
            // Excel Table View
            ExcelShotTableView(shots = filteredShots, onCopyPrompt = { p, l -> onCopyPrompt(context, p, l) }, onEnhancePrompt = onEnhancePrompt)
        }
    }
}

@Composable
fun DirectorShotCard(
    shot: DirectorShot,
    onCopyPrompt: (String, String) -> Unit,
    onEnhancePrompt: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineBorder, CineSurfaceVariant))),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Shot ID, Timecode, Duration Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(CineGold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = shot.shotId,
                            color = Color(0xFF0F172A),
                            fontWeight = FontWeight.Black,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = shot.sceneId,
                        color = CineTextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = shot.timeCode,
                        color = CineCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .background(CineSurfaceVariant, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${shot.durationSec}s",
                            color = CineTextPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Shot Type & Technical Camera Specs
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CineSurfaceVariant, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Videocam, contentDescription = null, tint = CineGold, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = shot.shotType,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = CineGoldLight
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Angle: ${shot.cameraAngle}  •  Move: ${shot.cameraMovement}  •  Lens: ${shot.lensSuggestion}",
                    fontSize = 11.sp,
                    color = CineTextPrimary
                )
                Text(
                    text = "Composition: ${shot.composition}",
                    fontSize = 11.sp,
                    color = CineTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Acting & Body Language Section
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Face, contentDescription = null, tint = CineCyan, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "HUMAN ACTING DIRECTIVES",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = CineCyan,
                        letterSpacing = 0.5.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Actors: ${shot.charactersPresent} (${shot.characterPosition})",
                    fontSize = 11.sp,
                    color = CineTextSecondary
                )
                Text(
                    text = "Facial Expression: ${shot.facialExpression}",
                    fontSize = 12.sp,
                    color = CineTextPrimary
                )
                Text(
                    text = "Body Language: ${shot.bodyLanguage}",
                    fontSize = 12.sp,
                    color = CineTextPrimary
                )
            }

            // Dialogue if any
            if (shot.dialogue.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CineBackgroundDark, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = shot.dialogue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = CineGold
                        )
                        Text(
                            text = "Voice Direction: ${shot.voiceDirection}",
                            fontSize = 11.sp,
                            color = CineTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Lighting, Color Grade & Sound
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Lighting & Mood", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CineTextSecondary)
                    Text(shot.lighting, fontSize = 11.sp, color = CineTextPrimary)
                    Text("Grade: ${shot.colorGrade}", fontSize = 10.sp, color = CineTextSecondary)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Sound & Foley", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CineTextSecondary)
                    Text(shot.soundDesign, fontSize = 11.sp, color = CineTextPrimary)
                    Text("Cut to: ${shot.transition}", fontSize = 10.sp, color = CineTextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Continuity Warning / Note
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CineSurfaceElevated, RoundedCornerShape(6.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Lock, contentDescription = null, tint = CineGold, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Continuity Lock: ${shot.continuityNotes}",
                    fontSize = 11.sp,
                    color = CineTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // AI Video Generation Prompt Box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, CineCyan.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                    .background(CineBackgroundDark, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = CineCyan, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AI VIDEO GENERATION PROMPT",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = CineCyan,
                            letterSpacing = 0.5.sp
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Button(
                            onClick = onEnhancePrompt,
                            colors = ButtonDefaults.buttonColors(containerColor = CineGoldDark, contentColor = CineGoldLight),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier
                                .height(28.dp)
                                .testTag("ai_enhance_prompt_${shot.shotId}")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Gemini AI", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { onCopyPrompt(shot.aiVideoPrompt, "Prompt ${shot.shotId}") },
                            colors = ButtonDefaults.buttonColors(containerColor = CineCyan, contentColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier
                                .height(28.dp)
                                .testTag("copy_shot_prompt_${shot.shotId}")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Copy", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = shot.aiVideoPrompt,
                    fontSize = 12.sp,
                    color = CineTextPrimary,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Avoid / Negative: ${shot.avoidList}",
                    fontSize = 10.sp,
                    color = CineTextMuted,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun ExcelShotTableView(
    shots: List<DirectorShot>,
    onCopyPrompt: (String, String) -> Unit,
    onEnhancePrompt: (String) -> Unit
) {
    val horizontalScrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(horizontalScrollState)
    ) {
        Column(modifier = Modifier.width(1550.dp)) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CineSurfaceElevated)
                    .padding(vertical = 10.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableTextHeader("Shot ID", 80.dp)
                TableTextHeader("Time", 90.dp)
                TableTextHeader("Type", 120.dp)
                TableTextHeader("Angle & Move", 140.dp)
                TableTextHeader("Lens", 110.dp)
                TableTextHeader("Characters", 120.dp)
                TableTextHeader("Facial & Body Acting", 220.dp)
                TableTextHeader("Dialogue", 160.dp)
                TableTextHeader("Lighting & Sound", 180.dp)
                TableTextHeader("AI Prompts (Gemini AI & Copy)", 260.dp)
            }

            HorizontalDivider(color = CineBorder, thickness = 1.dp)

            // Rows
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(shots, key = { it.shotId }) { shot ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (shots.indexOf(shot) % 2 == 0) CineSurfaceDark else CineBackgroundDark)
                            .padding(vertical = 10.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TableTextCell(shot.shotId, 80.dp, CineGold, FontWeight.Bold)
                        TableTextCell(shot.timeCode, 90.dp, CineCyan)
                        TableTextCell(shot.shotType, 120.dp, CineTextPrimary)
                        TableTextCell("${shot.cameraAngle}\n${shot.cameraMovement}", 140.dp, CineTextSecondary)
                        TableTextCell(shot.lensSuggestion, 110.dp, CineTextPrimary)
                        TableTextCell(shot.charactersPresent, 120.dp, CineTextSecondary)
                        TableTextCell("${shot.facialExpression}\n${shot.bodyLanguage}", 220.dp, CineTextPrimary)
                        TableTextCell(shot.dialogue.ifBlank { "[No dialogue]" }, 160.dp, CineGoldLight)
                        TableTextCell("${shot.lighting}\n${shot.soundDesign}", 180.dp, CineTextSecondary)

                        Row(modifier = Modifier.width(260.dp), horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                            Button(
                                onClick = { onEnhancePrompt(shot.shotId) },
                                colors = ButtonDefaults.buttonColors(containerColor = CineGoldDark, contentColor = CineGoldLight),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                                modifier = Modifier.height(28.dp)
                            ) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Gemini AI", fontSize = 10.sp)
                            }

                            Button(
                                onClick = { onCopyPrompt(shot.aiVideoPrompt, "Prompt ${shot.shotId}") },
                                colors = ButtonDefaults.buttonColors(containerColor = CineCyan, contentColor = Color(0xFF0F172A)),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                                modifier = Modifier.height(28.dp)
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Copy", fontSize = 10.sp)
                            }
                        }
                    }
                    HorizontalDivider(color = CineBorder.copy(alpha = 0.4f), thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun TableTextHeader(title: String, width: androidx.compose.ui.unit.Dp) {
    Text(
        text = title,
        fontWeight = FontWeight.Black,
        fontSize = 11.sp,
        color = CineGold,
        modifier = Modifier.width(width)
    )
}

@Composable
fun TableTextCell(
    text: String,
    width: androidx.compose.ui.unit.Dp,
    color: Color = CineTextPrimary,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        fontSize = 11.sp,
        color = color,
        fontWeight = fontWeight,
        maxLines = 3,
        modifier = Modifier.width(width)
    )
}
