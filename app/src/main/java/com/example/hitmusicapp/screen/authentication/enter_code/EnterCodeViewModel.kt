package com.example.hitmusicapp.screen.authentication.enter_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import com.google.android.material.color.utilities.MaterialDynamicColors.onError
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class EnterCodeViewModel : BaseViewModel() {
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

    suspend fun sendOTP(): DataResult<String> {
        var token = ""
        return suspendCoroutine {
            ApiHelper.getInstance().verifyOTP(body).enqueue(
                object : Callback<ApiResponse<String>> {
                    override fun onResponse(
                        call: Call<ApiResponse<String>>,
                        response: Response<ApiResponse<String>>
                    ) {
                        if (response.isSuccessful && response.body()?.tokenResetPassword != null) {
                            token = response.body()?.tokenResetPassword!!
                        }
                        it.resume(DataResult.Success(token))
                    }

                    override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
                        it.resume(DataResult.Success(token))
                    }
                }
            )
        }
    }

    fun getData() {
        executeTask(
            request = { sendOTP() },
            onSuccess = {
                _token.value = it
            },
            onError = {}
        )
    }


    //    fun sendOTP(): DataResult<Any> {
//        val apiService = ApiHelper.getInstance().verifyOTP(body).enqueue(
//            object : Callback<ApiResponse<String>> {
//                override fun onResponse(
//                    call: Call<ApiResponse<String>>,
//                    response: Response<ApiResponse<String>>
//                ) {
//                    if (response.isSuccessful) {
//                        _token.value = response.body()?.tokenResetPassword
//                    } else {
//                        if (response.body()?.status == 400) {
//                            _message.value = "OTP has expired"
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
//
//                }
//
//            }
//        )
//        return DataResult.Success<String>(token.value.toString())
//    }


}