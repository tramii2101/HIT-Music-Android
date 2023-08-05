package com.example.hitmusicapp.entities

import com.google.gson.annotations.SerializedName


data class Category (

    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("image" ) var image : String? = null,
    @SerializedName("id"    ) var id    : String? = null

)