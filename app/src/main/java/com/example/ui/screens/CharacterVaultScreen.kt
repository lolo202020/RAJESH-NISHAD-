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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.CharacterEntity
import com.example.ui.CharacterVaultViewModel
import com.example.ui.theme.*

@Composable
fun CharacterVaultScreen(
    viewModel: CharacterVaultViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val savedProfiles by viewModel.savedProfiles.collectAsStateWithLifecycle()
    val name by viewModel.nameInput.collectAsStateWithLifecycle()
    val age by viewModel.ageInput.collectAsStateWithLifecycle()
    val traits by viewModel.traitsInput.collectAsStateWithLifecycle()
    val actingStyle by viewModel.actingStyleInput.collectAsStateWithLifecycle()
    val voice by viewModel.voiceInput.collectAsStateWithLifecycle()
    val dialogue by viewModel.dialogueInput.collectAsStateWithLifecycle()

    var showSuccessMessage by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CineBackgroundDark)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(CineSurfaceDark, CineSurfaceVariant)),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, CineGold.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, contentDescription = null, tint = CineGold, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "LOCAL ROOM CHARACTER VAULT",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = CineGold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Store and persist custom character profiles, personality traits, and dialogue history offline via Room Database.",
                        fontSize = 12.sp,
                        color = CineTextSecondary
                    )
                }
            }
        }

        // Input Form Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CineSurfaceDark),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CREATE / ADD CHARACTER PROFILE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CineCyan,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = viewModel::updateName,
                        label = { Text("Character Name", fontSize = 12.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("vault_name_input"),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = age,
                        onValueChange = viewModel::updateAge,
                        label = { Text("Visual Age & Description", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = traits,
                        onValueChange = viewModel::updateTraits,
                        label = { Text("Personality Traits & Facial Features", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = actingStyle,
                        onValueChange = viewModel::updateActingStyle,
                        label = { Text("Acting Style & Performance Notes", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = voice,
                        onValueChange = viewModel::updateVoice,
                        label = { Text("Voice Pattern & Delivery", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = dialogue,
                        onValueChange = viewModel::updateDialogue,
                        label = { Text("Dialogue History / Iconic Lines", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = vaultTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        minLines = 2
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.saveProfile {
                                showSuccessMessage = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("vault_save_button"),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CineGold, contentColor = Color(0xFF0F172A))
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SAVE CHARACTER TO ROOM DATABASE", fontWeight = FontWeight.Black, fontSize = 12.sp)
                    }

                    if (showSuccessMessage) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Character profile saved successfully to Room database!",
                            fontSize = 11.sp,
                            color = CineGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Saved Profiles Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SAVED CHARACTERS IN VAULT (${savedProfiles.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = CineTextPrimary
                )
            }
        }

        if (savedProfiles.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No character profiles saved in Room yet.", color = CineTextSecondary, fontSize = 13.sp)
                }
            }
        }

        items(savedProfiles, key = { it.id }) { profile ->
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
                            Text(profile.characterName, fontWeight = FontWeight.Black, fontSize = 16.sp, color = CineGold)
                            Text(profile.visualAge, fontSize = 11.sp, color = CineCyan)
                        }

                        IconButton(
                            onClick = { viewModel.deleteProfile(profile) },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = CineRed, modifier = Modifier.size(18.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    VaultDetailRow("Traits:", profile.facialFeatures)
                    VaultDetailRow("Acting Style:", profile.actingStyle)
                    VaultDetailRow("Voice:", profile.voicePattern)
                    VaultDetailRow("Dialogue:", profile.dialogueHistory)
                }
            }
        }
    }
}

@Composable
fun vaultTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = CineGold,
    unfocusedBorderColor = CineBorder,
    focusedTextColor = CineTextPrimary,
    unfocusedTextColor = CineTextPrimary,
    focusedContainerColor = CineBackgroundDark,
    unfocusedContainerColor = CineBackgroundDark
)

@Composable
fun VaultDetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = CineTextSecondary, modifier = Modifier.width(90.dp))
        Text(value, fontSize = 11.sp, color = CineTextPrimary, modifier = Modifier.weight(1f))
    }
}
