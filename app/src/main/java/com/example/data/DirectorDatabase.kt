package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class DirectorDatabase : RoomDatabase() {
    abstract fun directorDao(): DirectorDao

    companion object {
        @Volatile
        private var INSTANCE: DirectorDatabase? = null

        fun getDatabase(context: Context): DirectorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DirectorDatabase::class.java,
                    "cinedirector_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
