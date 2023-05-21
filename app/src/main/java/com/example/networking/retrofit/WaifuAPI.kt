package com.example.networking.retrofit

import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query

interface WaifuAPI {
    @GET("/search")
    suspend fun getRandomPic() : WaifuList

    @GET("/search")
    suspend fun getPicByTag(@Query("included_tags") tag : String) : WaifuList
}