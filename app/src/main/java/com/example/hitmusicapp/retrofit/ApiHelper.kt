package com.example.hitmusicapp.retrofit

import com.example.hitmusicapp.utils.common.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {

    /** Create retrofit instance
     * Because the call to create() function on a Retrofit object is expensive and the app needs only one instance of Retrofit API service
     * so we use object declaration*/

    fun getInstance(): ApiService {
        val API: ApiService by lazy {
            Retrofit.Builder().baseUrl(ApiConstants.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
        return API
    }
}