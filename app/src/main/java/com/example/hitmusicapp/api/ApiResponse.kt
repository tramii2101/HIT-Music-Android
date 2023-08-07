package com.example.hitmusicapp.api

data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
    val tokenVerifyOTP: String?,
    val tokenResetPassword: String?,
    val accessToken: String?
)