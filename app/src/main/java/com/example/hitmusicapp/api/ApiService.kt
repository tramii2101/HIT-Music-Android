package com.example.hitmusicapp.api

import com.example.hitmusicapp.utils.common.ApiConstants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST(ApiConstants.FORGOT_PASSWORD)
    fun forgetPassword(
        @Body email: Map<String, String>
    ): Call<ApiResponse<String>>

    @POST(ApiConstants.VERIFY_OTP)
    fun verifyOTP(
        @Body body: Map<String, String>,
    ): Call<ApiResponse<String>>

    @POST(ApiConstants.RESET_PASSWORD)
    fun resetPassword(
        @Body body: Map<String, String>
    ): Call<ApiResponse<String>>
}