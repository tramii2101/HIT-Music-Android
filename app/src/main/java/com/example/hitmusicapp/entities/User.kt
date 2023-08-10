package com.example.hitmusicapp.entities

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("username")
    var username: String,
    @SerializedName("fullname")
    var fullname: String,
    @SerializedName("password")
    var password: String ,
    @SerializedName("email")
    var email: String ,
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("dateOfBirth")
    var dateOfBirth: String? = null,
    @SerializedName("gender")
    var gender: String? = "Nam",
    @SerializedName("role")
    var role: String = "user",
    @SerializedName("activeVip")
    var activeVip: Boolean? = false,
    @SerializedName("id")
    val id: String
)
