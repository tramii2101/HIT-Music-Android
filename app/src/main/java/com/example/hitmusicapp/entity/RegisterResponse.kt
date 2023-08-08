package com.example.hitmusicapp.entity

data class RegisterResponse(
    val status: Int,
    val message: String,
    val data: RegisterResponseData
)
