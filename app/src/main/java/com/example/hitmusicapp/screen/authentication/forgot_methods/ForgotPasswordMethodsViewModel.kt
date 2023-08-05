package com.example.hitmusicapp.screen.authentication.forgot_methods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordMethodsViewModel : BaseViewModel() {

    var email = ""

    val message by lazy {
        MutableLiveData<String?>()
    }


    private val body by lazy {
        mapOf(
            "email" to email
        )
    }

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?>
        get() = _token

    fun getTokenVerifyOTP(email: String) {
        val responseToken = ApiHelper.getInstance().forgetPassword(body)
            .enqueue(object : Callback<ApiResponse<String>> {
                override fun onResponse(
                    call: Call<ApiResponse<String>>,
                    response: Response<ApiResponse<String>>
                ) {
                    if (response.isSuccessful) {
                        _token.value = response.body()?.tokenVerifyOTP
                    } else {
                        if (response.code() == 404)
                            message.value = response.body()?.message
                        else {
                            message.value = "Error"
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
                    message.value = "Oops! Something went wrong!"
                }
            })
    }

}