package com.example.networking.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.networking.retrofit.Waifu;
import com.example.networking.room.entities.WaifuEntity;

import java.util.List;

@Dao
interface WaifuDao {
    @Query("SELECT * FROM waifus")
    fun getAllWaifus() : List<WaifuEntity>?

    @Insert
    fun addWaifu(waifuEntity :WaifuEntity)
}