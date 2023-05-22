package com.example.networking.room.entities

import com.example.networking.retrofit.Waifu
import com.example.networking.room.WaifuDao
import com.example.networking.room.WaifuRepository

class RoomWaifuRepository (
    private  val waifuDao : WaifuDao
) : WaifuRepository
{
    override fun getAllWaifus(): List<Waifu?>? {
        val allWaifus = waifuDao.getAllWaifus()
        if (allWaifus != null)
        {
            return allWaifus.map { waifuEntity -> waifuEntity.toWaifu() }
        }
        else
        {
            return listOf<Waifu>()
        }
    }

    override fun addWaifu(waifu: Waifu) {
        val entity = WaifuEntity.fromWaifu(waifu);
        waifuDao.addWaifu(entity);
    }
}
