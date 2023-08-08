package com.example.hitmusicapp.entity

data class RegisterRequest(
    val fullname: String,
    val username: String,
    val email: String,
    val password: String,
    val passwordAgain: String
)
