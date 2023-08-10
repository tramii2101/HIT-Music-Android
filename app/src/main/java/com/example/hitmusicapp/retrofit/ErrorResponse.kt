package com.example.hitmusicapp.retrofit

import com.google.gson.annotations.SerializedName


data class ErrorResponse (

    @SerializedName("status"  ) var status  : Int? ,
    @SerializedName("message" ) var message : String? 

)