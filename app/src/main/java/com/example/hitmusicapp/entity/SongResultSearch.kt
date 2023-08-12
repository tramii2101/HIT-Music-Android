package com.example.hitmusicapp.entity

import com.example.hitmusicapp.entities.Singer
import com.google.gson.annotations.SerializedName

data class SongResultSearch(

    @SerializedName("singer")       var singer: Singer = Singer(),
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("country"     ) var country     : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("audio"       ) var audio       : String? = null,
    @SerializedName("year"        ) var year        : Int?    = null,
    @SerializedName("length"      ) var length      : Int?    = null,
    @SerializedName("playCount"   ) var playCount   : Int?    = null,
    @SerializedName("category"    ) var category    : String? = null,
    @SerializedName("createdAt"   ) var createdAt   : String? = null,
    @SerializedName("id"          ) var id          : String? = null
)
