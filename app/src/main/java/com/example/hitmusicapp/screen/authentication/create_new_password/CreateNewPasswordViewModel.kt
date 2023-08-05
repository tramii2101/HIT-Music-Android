package com.example.hitmusicapp.screen.authentication.create_new_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewPasswordViewModel : ViewModel() {
    var newPassword = ""
    var tokenResetPassword = ""
    private val _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    val body = mapOf(
        "newPassword" to newPassword,
        "tokenResetPassword" to tokenResetPassword
    )

    fun createNewPassword() {
        val api = ApiHelper.getInstance().resetPassword(body).enqueue(
            object : Callback<ApiResponse<String>> {
                override fun onResponse(
                    call: Call<ApiResponse<String>>,
                    response: Response<ApiResponse<String>>
                ) {
                    if (response.isSuccessful) {
                        _message.value = response.body()?.message
                    } else {
                        _message.value = "Oops! Something went wrong"
                    }
                }

                override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
                    _message.value = "Oops! Something went wrong"
                }

            }
        )
    }
}