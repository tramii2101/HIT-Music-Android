package com.example.hitmusicapp.entities

import com.google.gson.annotations.SerializedName

data class Results<T>(
    @SerializedName("results")
    var listData: MutableList<T>
)