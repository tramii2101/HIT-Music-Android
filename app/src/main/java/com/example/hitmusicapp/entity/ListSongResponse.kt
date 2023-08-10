package com.example.hitmusicapp.entity

import com.example.hitmusicapp.entities.Song
import com.google.gson.annotations.SerializedName

data class ListSongResponse(
    @SerializedName("musics")
    var listMusic: MutableList<Song>
)
