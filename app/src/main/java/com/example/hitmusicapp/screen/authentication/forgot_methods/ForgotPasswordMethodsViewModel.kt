package com.example.hitmusicapp.screen.authentication.forgot_methods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import com.google.android.material.color.utilities.MaterialDynamicColors.onError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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


    suspend fun getTokenVerifyOTP(): DataResult<Any> {
        var token = ""
        return suspendCoroutine {
            ApiHelper.getInstance().forgetPassword(body).enqueue(
                object : Callback<ApiResponse<String>> {
                    override fun onResponse(
                        call: Call<ApiResponse<String>>,
                        response: Response<ApiResponse<String>>
                    ) {
                        if (response.isSuccessful && response.body()?.tokenVerifyOTP != null) {
                            token = response.body()?.tokenVerifyOTP!!
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
            request = { getTokenVerifyOTP() },
            onSuccess = {
                _token.value = it.toString()
            },
            onError = {}
        )
    }


    //    fun getTokenVerifyOTP(email: String) {
//        val responseToken = ApiHelper.getInstance().forgetPassword(body)
//            .enqueue(object : Callback<ApiResponse<String>> {
//                override fun onResponse(
//                    call: Call<ApiResponse<String>>,
//                    response: Response<ApiResponse<String>>
//                ) {
//                    if (response.isSuccessful) {
//                        _token.value = response.body()?.tokenVerifyOTP
//                    } else {
//                        if (response.code() == 404)
//                            message.value = response.body()?.message
//                        else {
//                            message.value = "Error"
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
//                    message.value = "Oops! Something went wrong!"
//                }
//            })
//    }

}