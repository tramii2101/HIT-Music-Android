package com.example.hitmusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.api.ApiHelper
import com.example.hitmusicapp.api.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entities.Results
import com.example.hitmusicapp.entities.Singer
import com.example.hitmusicapp.entities.Song
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeViewModel : BaseViewModel() {
    var accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NGMyNzZiNGM1Y2E4ZTMwYTZiMWY3ZGYiLCJpYXQiOjE2OTE0ODcyNjEsImV4cCI6MTY5MTU3MzY2MX0.icnjllDZntuoGSMEimow2NPblgFUBIgaz8SGCw9Mk9c"

    val loading by lazy {
        MutableLiveData<Boolean>()
    }

    private val _listCategory = MutableLiveData<MutableList<Category>>()
    val listCategory: LiveData<MutableList<Category>>
        get() = _listCategory

    private val _listSong = MutableLiveData<MutableList<Song>>()
    val listSong: LiveData<MutableList<Song>>
        get() = _listSong

    private val _listSinger = MutableLiveData<MutableList<Singer>>()
    val listSinger: LiveData<MutableList<Singer>>
        get() = _listSinger

    suspend fun getListSong(): DataResult<MutableList<Song>> {
        var list = mutableListOf<Song>()
        return suspendCoroutine {
            ApiHelper.getInstance().getListMusic(accessToken).enqueue(
                object : Callback<ApiResponse<MutableList<Song>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<MutableList<Song>>>,
                        response: Response<ApiResponse<MutableList<Song>>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null) {
                            list = response.body()?.data!!
                            loading.value = false
                        }
                        it.resume(DataResult.Success(list))
                        loading.value = false
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<MutableList<Song>>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                        loading.value = false
                    }
                }
            )
        }
    }


    fun getSongAtHome() {
        executeTask(
            request = { getListSong() },
            onSuccess = {
                _listSong.value = it

            },
            onError = {

            },
            showLoading = true
        )
    }

    suspend fun getCategories() : DataResult<MutableList<Category>>{
        var list = mutableListOf<Category>()
        return suspendCoroutine {
            ApiHelper.getInstance().getListCategory(accessToken).enqueue(
                object : Callback<ApiResponse<MutableList<Category>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<MutableList<Category>>>,
                        response: Response<ApiResponse<MutableList<Category>>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null)
                            list = response.body()?.data!!
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<MutableList<Category>>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                    }
                }
            )
        }
    }

    fun getAllCategory(){
        executeTask(
            request = {getCategories()},
            onSuccess = {
                _listCategory.value = it
            },
            onError = {},
        )
    }


    suspend fun getListSingers() : DataResult<MutableList<Singer>>{
        var list = mutableListOf<Singer>()
        return suspendCoroutine {
            ApiHelper.getInstance().getListSinger(accessToken).enqueue(
                object : Callback<ApiResponse<Results<Singer>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<Results<Singer>>>,
                        response: Response<ApiResponse<Results<Singer>>>
                    ) {
                        val x = response.body()?.data
                        if (response.isSuccessful && response.body()?.data != null) {
                            list = response.body()?.data!!.listData
                        }
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<Results<Singer>>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                    }

                }
            )
        }
    }


    fun getAllSinger() {
        executeTask(
            request = {getListSingers()},
            onSuccess = {
                _listSinger.value = it
            },
            onError = {},
            showLoading = true
        )
    }

    fun getData() {
        getSongAtHome()
        getAllSinger()
        getAllCategory()
    }

}