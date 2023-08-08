package com.example.hitmusicapp.utils.common

object ApiConstants {
    const val BASE = "https://hitmusic.tech"

    // Auth API
    const val REGISTER = "/api/auth/register"
    const val LOGIN = "/api/auth/login"
    const val GET_PROFILE = "/api/auth/profile"
    const val UPDATE_PROFILE = "/api/auth/profile"
    const val CHANGE_PASSWORD = "/api/auth/change-password"
    const val FORGOT_PASSWORD = "/api/auth/forgot-password"
    const val VERIFY_OTP = "/api/auth/verify-otp"
    const val RESET_PASSWORD = "/api/auth/reset-password"

    const val GET_LIST_MUSIC = "/api/musics"
    const val SINGER = "/api/singers"
    const val GET_LIST_CATEGORY = "/api/categories"
    const val GET_LIST_SINGER = "/api/singers"

}