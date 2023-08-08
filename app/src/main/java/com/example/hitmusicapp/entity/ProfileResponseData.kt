package com.example.hitmusicapp.entity

data class ProfileResponseData(
    val username: String,
    val fullname: String,
    val email: String,
    val avatar: String,
    val dateOfBirth: String,
    val gender: String,
    val role: String,
    val activeVip: Boolean,
    val id: String
)
