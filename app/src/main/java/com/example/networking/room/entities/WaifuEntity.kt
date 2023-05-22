package com.example.networking.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.networking.retrofit.Waifu


@Entity(
    tableName = "waifus"
)
data class WaifuEntity (
    @PrimaryKey(autoGenerate = true)  val id: Long,
    val url: String
) {
    fun toWaifu() : Waifu = Waifu(
        url = url
    )

    companion object {
        fun fromWaifu(waifu : Waifu) : WaifuEntity = WaifuEntity(
            id = 0,
            url = waifu.url
        )
    }
}