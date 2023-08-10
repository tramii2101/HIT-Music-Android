package com.example.hitmusicapp.entities

import com.google.gson.annotations.SerializedName
data class Singer (

    @SerializedName("fullname"    ) var fullname    : String? = null,
    @SerializedName("birthday"    ) var birthday    : String? = null,
    @SerializedName("gender"      ) var gender      : String? = null,
    @SerializedName("avatar"      ) var avatar      : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("id"          ) var id          : String? = null

)
