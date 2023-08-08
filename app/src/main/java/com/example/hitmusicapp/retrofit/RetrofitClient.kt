package com.example.hitmusicapp.retrofit

import com.example.hitmusicapp.utils.extension.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    const val URL = "https://hitmusic.tech"
    var INSTANCE: Retrofit? = null
    fun getInstance(): Retrofit =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: retrofitBuilder().also {
                INSTANCE = it
            }
        }

    private fun retrofitBuilder(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(Constant.READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(Constant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
        return Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }
}