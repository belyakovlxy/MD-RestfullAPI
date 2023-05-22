package com.example.networking.room

import com.example.networking.retrofit.Waifu

interface WaifuRepository {
    fun getAllWaifus() : List<Waifu?>?

    fun addWaifu(waifu: Waifu)
}
