package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DirectorDao {
    @Query("SELECT * FROM character_profiles ORDER BY timestamp DESC")
    fun getAllProfiles(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: CharacterEntity)

    @Delete
    suspend fun deleteProfile(profile: CharacterEntity)

    @Query("DELETE FROM character_profiles")
    suspend fun clearAllProfiles()
}
