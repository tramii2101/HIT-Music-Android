package com.example.hitmusicapp.api

import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entities.Results
import com.example.hitmusicapp.entities.Singer
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.utils.common.ApiConstants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
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
    ): Call<ApiResponse<Nothing>>

    @GET(ApiConstants.GET_LIST_MUSIC)
    fun getListMusic(
        @Header("Authorization") token: String,
    ): Call<ApiResponse<MutableList<Song>>>

    @GET(ApiConstants.GET_LIST_CATEGORY)
    fun getListCategory(@Header("Authorization") accessToken: String): Call<ApiResponse<MutableList<Category>>>

    @GET(ApiConstants.GET_LIST_SINGER)
    fun getListSinger(@Header("Authorization") accessToken: String): Call<ApiResponse<Results<Singer>>>
}