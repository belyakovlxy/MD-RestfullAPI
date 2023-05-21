package com.example.networking.retrofit

data class Waifu(
    val url : String
)

data class WaifuList(
    val images: List<Waifu>
)
