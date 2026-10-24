package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_profiles")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val characterName: String,
    val visualAge: String,
    val facialFeatures: String,
    val actingStyle: String,
    val voicePattern: String,
    val dialogueHistory: String,
    val timestamp: Long = System.currentTimeMillis()
)
