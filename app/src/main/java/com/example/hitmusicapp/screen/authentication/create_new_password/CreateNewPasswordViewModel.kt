package com.example.hitmusicapp.screen.authentication.create_new_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CreateNewPasswordViewModel : BaseViewModel() {
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
            object : Callback<ApiResponse<Nothing>> {
                override fun onResponse(
                    call: Call<ApiResponse<Nothing>>,
                    response: Response<ApiResponse<Nothing>>
                ) {
                    if (response.isSuccessful) {
                        _message.value = response.body()?.message
                    } else {
                        _message.value = "Oops! Something went wrong"
                    }
                }
                override fun onFailure(call: Call<ApiResponse<Nothing>>, t: Throwable) {
                    _message.value = "Oops! Something went wrong..."
                }

            }
        )
    }
}