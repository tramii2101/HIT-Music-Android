package com.example.hitmusicapp.retrofit

import com.example.hitmusicapp.entity.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("/api/auth/login")
    fun login(@Body loginRequest: LoginRequest?): Call<LoginResponse>

    @POST("/api/auth/register")
    fun register(@Body registerRequest: RegisterRequest?): Call<RegisterResponse>

    @GET("/api/auth/profile")
    fun getProfile(@Header("Authorization") accessToken: String): Call<ProfileResponse>


//    @GET("/users")
//    suspend fun getUsers(): List<User>
//    suspend fun updateUser(@Query("id") id: String)
}