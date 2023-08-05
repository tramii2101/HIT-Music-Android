package com.example.hitmusicapp.screen.authentication.enter_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterCodeViewModel : ViewModel() {
    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?>
        get() = _token

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?>
        get() = _message

    var otp: String = ""
    var tokenVerifyOTP = ""


    private val body by lazy {
        mapOf(
            "otp" to otp,
            "tokenVerifyOTP" to tokenVerifyOTP
        )
    }

    fun sendOTP() {
        val apiService = ApiHelper.getInstance().verifyOTP(body).enqueue(
            object : Callback<ApiResponse<String>> {
                override fun onResponse(
                    call: Call<ApiResponse<String>>,
                    response: Response<ApiResponse<String>>
                ) {
                    if (response.isSuccessful) {
                        _token.value = response.body()?.tokenResetPassword
                    } else {
                        if (response.body()?.status == 400) {
                            _message.value = "OTP has expired"
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {

                }

            }
        )
    }
}