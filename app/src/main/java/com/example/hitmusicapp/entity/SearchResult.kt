package com.example.hitmusicapp.entity

import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entities.Singer
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("musics")
    var listSong: MutableList<SongResultSearch>,
    @SerializedName("singers")
    var listSinger: MutableList<Singer>,
    @SerializedName("categories")
    var listCategory: MutableList<Category>
)
