package com.example.networking.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.networking.room.entities.WaifuEntity

@Database(
    version = 1,
    entities = [
        WaifuEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun getWaifuDao() : WaifuDao
}