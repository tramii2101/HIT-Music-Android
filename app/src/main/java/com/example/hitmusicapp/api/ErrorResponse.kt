package com.example.hitmusicapp.api

import com.google.gson.annotations.SerializedName


data class ErrorResponse (

    @SerializedName("status"  ) var status  : Int? ,
    @SerializedName("message" ) var message : String? 

)