package com.example.hitmusicapp.retrofit

import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entity.ResultsHomeResponse
import com.example.hitmusicapp.entities.Singer
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.ListSongResponse
import com.example.hitmusicapp.entity.SongResponse
import com.example.hitmusicapp.utils.common.ApiConstants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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
    ): Call<ApiResponse<ResultsHomeResponse<Song>>>

    @GET(ApiConstants.GET_LIST_CATEGORY)
    fun getListCategory(@Header("Authorization") accessToken: String): Call<ApiResponse<ResultsHomeResponse<Category>>>

    @GET(ApiConstants.GET_LIST_SINGER)
    fun getListSinger(@Header("Authorization") accessToken: String): Call<ApiResponse<ResultsHomeResponse<Singer>>>

    @GET(ApiConstants.GET_SONG_BY_SINGER)
    fun getSongBySinger(
        @Header("Authorization") accessToken: String,
        @Path("singerId") singerId: String
    ): Call<ApiResponse<ListSongResponse>>

    @GET(ApiConstants.GET_SONG_BY_ID)
    fun getSongById(@Header("Authorization") accessToken: String, @Path("ID") id: String) : Call<ApiResponse<SongResponse>>
}