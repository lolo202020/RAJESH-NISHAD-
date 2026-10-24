package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProductionPackage
import com.example.service.MasterpiecePresets
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectorTopBar(
    currentTitle: String,
    onLoadPreset: (ProductionPackage) -> Unit,
    onNewProjectClick: () -> Unit
) {
    var showPresetsMenu by remember { mutableStateOf(false) }

    Surface(
        color = CineSurfaceDark,
        tonalElevation = 6.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Branding
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(
                                brush = Brush.linearGradient(listOf(CineGold, CineGoldDark)),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MovieFilter,
                            contentDescription = "Clapperboard icon",
                            tint = Color(0xFF0F172A),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "CINEDIRECTOR",
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = CineTextPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(CineCyan.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "AI PRO",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CineCyan
                                )
                            }
                        }
                        Text(
                            text = currentTitle.take(30) + if (currentTitle.length > 30) "..." else "",
                            fontSize = 11.sp,
                            color = CineTextSecondary,
                            maxLines = 1
                        )
                    }
                }

                // Actions: Presets and New
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        FilledTonalButton(
                            onClick = { showPresetsMenu = true },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = CineSurfaceVariant,
                                contentColor = CineGoldLight
                            ),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("presets_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Presets", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }

                        DropdownMenu(
                            expanded = showPresetsMenu,
                            onDismissRequest = { showPresetsMenu = false },
                            modifier = Modifier.background(CineSurfaceVariant)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(
                                            "Monsoon Reminiscence (30s)",
                                            fontWeight = FontWeight.Bold,
                                            color = CineTextPrimary
                                        )
                                        Text("Romantic Drama | 9:16 Vertical", fontSize = 11.sp, color = CineTextSecondary)
                                    }
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.WaterDrop, contentDescription = null, tint = CineCyan)
                                },
                                onClick = {
                                    onLoadPreset(MasterpiecePresets.mumbaiMonsoonEncounter)
                                    showPresetsMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(
                                            "Floor 13: Ceiling Knock (30s)",
                                            fontWeight = FontWeight.Bold,
                                            color = CineTextPrimary
                                        )
                                        Text("Psychological Horror | 16:9 Landscape", fontSize = 11.sp, color = CineTextSecondary)
                                    }
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.NightlightRound, contentDescription = null, tint = CineRed)
                                },
                                onClick = {
                                    onLoadPreset(MasterpiecePresets.horrorCorridorKnock)
                                    showPresetsMenu = false
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButton(
                        onClick = onNewProjectClick,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("new_project_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "New Production Project",
                            tint = CineGold
                        )
                    }
                }
            }

            HorizontalDivider(color = CineBorder.copy(alpha = 0.5f), thickness = 1.dp)
        }
    }
}
