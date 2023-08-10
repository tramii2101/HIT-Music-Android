package com.example.hitmusicapp.entity

import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entities.Singer
import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("singer"      ) var singer      : Singer?   = Singer(),
    @SerializedName("title"       ) var title       : String?   = null,
    @SerializedName("description" ) var description : String?   = null,
    @SerializedName("country"     ) var country     : String?   = null,
    @SerializedName("image"       ) var image       : String?   = null,
    @SerializedName("audio"       ) var audio       : String?   = null,
    @SerializedName("year"        ) var year        : Int?      = null,
    @SerializedName("length"      ) var length      : Int?      = null,
    @SerializedName("playCount"   ) var playCount   : Int?      = null,
    @SerializedName("category"    ) var category    : Category? = Category(),
    @SerializedName("id"          ) var id          : String?   = null
)