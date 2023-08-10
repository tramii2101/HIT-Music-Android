package com.example.hitmusicapp.entity

import com.google.gson.annotations.SerializedName

data class ResultsHomeResponse<T>(
    @SerializedName("results")
    var listData: MutableList<T>,

)