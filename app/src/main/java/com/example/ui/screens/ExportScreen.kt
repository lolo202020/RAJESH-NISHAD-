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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProductionPackage
import com.example.model.QualityCheckItem
import com.example.ui.theme.*

@Composable
fun ExportScreen(
    production: ProductionPackage,
    onCopyAllPrompts: (Context) -> Unit,
    onCopyCsv: (Context, String) -> Unit,
    onCopyFullPackage: (Context, String) -> Unit,
    onExportGallery: (Context) -> Unit
) {
    val context = LocalContext.current

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
                icon = Icons.Default.Verified,
                badge = "PHASE 8 & OUTPUT 10-11",
                title = "QUALITY CONTROL & PRODUCTION EXPORT",
                subtitle = "Pre-generation checklist, full master script, copy-paste prompts, and gallery exports"
            )
        }

        // Master Video Assembly & Gallery Download Card (User Request)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineCyan, CineGold))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.VideoLibrary, contentDescription = null, tint = CineCyan, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MASTER VIDEO ASSEMBLY & GALLERY EXPORT",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineCyan,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Merge all ${production.shotList.size} generated AI shots & prompts into a unified master timeline and download directly to your device Gallery (Movies folder).",
                        fontSize = 12.sp,
                        color = CineTextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { onExportGallery(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = CineCyan, contentColor = Color(0xFF0F172A)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("merge_download_gallery_button")
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MERGE ALL AI SHOTS & DOWNLOAD TO GALLERY",
                            fontWeight = FontWeight.Black,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // Export Action Buttons
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CineGold, Color.Transparent))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ONE-TOUCH EXPORT ACTIONS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CineGold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { onCopyAllPrompts(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = CineGold, contentColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("copy_all_prompts_button")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Copy All Prompts", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        FilledTonalButton(
                            onClick = { onCopyCsv(context, "Spreadsheet CSV") },
                            colors = ButtonDefaults.filledTonalButtonColors(containerColor = CineSurfaceVariant, contentColor = CineCyan),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("export_csv_button")
                        ) {
                            Icon(Icons.Default.TableChart, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Copy Excel CSV", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { onCopyFullPackage(context, production.fullMasterExport) },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = CineTextPrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .testTag("copy_master_package_button")
                    ) {
                        Icon(Icons.Default.Description, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy Full Production Bible (Markdown)", fontSize = 12.sp)
                    }
                }
            }
        }

        // Automatic Quality Control Checklist
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Checklist, contentDescription = null, tint = CineGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AUTOMATED QUALITY CONTROL CHECKLIST",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = CineGreen,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    production.qualityChecklist.forEach { item ->
                        QCCheckRow(item = item)
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }

        // Master Package Text Preview
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "PRODUCTION PACKAGE TEXT PREVIEW",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CineTextSecondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CineBackgroundDark, RoundedCornerShape(8.dp))
                            .border(1.dp, CineBorder, RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = production.fullMasterExport,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = CineTextPrimary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QCCheckRow(item: QualityCheckItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CineSurfaceVariant, RoundedCornerShape(6.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (item.passStatus) Icons.Default.CheckCircle else Icons.Default.Warning,
            contentDescription = null,
            tint = if (item.passStatus) CineGreen else CineGold,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "[${item.category}] ",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CineCyan
                )
                Text(
                    text = item.checkpoint,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CineTextPrimary
                )
            }
            Text(
                text = item.notes,
                fontSize = 10.sp,
                color = CineTextSecondary
            )
        }
    }
}
