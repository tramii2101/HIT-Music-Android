package com.example.hitmusicapp.entities

import java.util.Date

data class User(
    val username: String,
    val fullName: String,
    val password: String,
    val email: String,
    val verifiedEmail: Boolean,
    val phoneNumber: String?,
    val avt: String?,
    val birthday: Date,
    val gender: String,
    val role: String,
    var activeVIP: Boolean = false
)
