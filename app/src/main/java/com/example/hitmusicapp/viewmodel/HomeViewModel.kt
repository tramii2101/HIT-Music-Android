package com.example.hitmusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.retrofit.ApiHelper
import com.example.hitmusicapp.retrofit.ApiResponse
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entity.ResultsHomeResponse
import com.example.hitmusicapp.entities.Singer
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.ListSongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeViewModel : BaseViewModel() {
    var accessToken =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NGMyNzZiNGM1Y2E4ZTMwYTZiMWY3ZGYiLCJpYXQiOjE2OTE1NzU1NDYsImV4cCI6MTY5MTY2MTk0Nn0.U9VPPvsVVc9hj8ZIFQZ9p1_LodnnCWgTpHWY0QbtyR0"

    var singerId = ""
    var categoryId = ""

    private val _song = MutableLiveData<Song>()
    val song: LiveData<Song>
        get() = _song

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
                object : Callback<ApiResponse<ResultsHomeResponse<Song>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<ResultsHomeResponse<Song>>>,
                        response: Response<ApiResponse<ResultsHomeResponse<Song>>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null) {
                            list = response.body()?.data!!.listData
                        }
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<ResultsHomeResponse<Song>>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
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

    suspend fun getCategories(): DataResult<MutableList<Category>> {
        var list = mutableListOf<Category>()
        return suspendCoroutine {
            ApiHelper.getInstance().getListCategory(accessToken).enqueue(
                object : Callback<ApiResponse<ResultsHomeResponse<Category>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<ResultsHomeResponse<Category>>>,
                        response: Response<ApiResponse<ResultsHomeResponse<Category>>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null)
                            list = response.body()?.data!!.listData
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<ResultsHomeResponse<Category>>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                    }
                }
            )
        }
    }

    fun getAllCategory() {
        executeTask(
            request = { getCategories() },
            onSuccess = {
                _listCategory.value = it
            },
            onError = {},
        )
    }


    suspend fun getListSingers(): DataResult<MutableList<Singer>> {
        var list = mutableListOf<Singer>()
        return suspendCoroutine {
            ApiHelper.getInstance().getListSinger(accessToken).enqueue(
                object : Callback<ApiResponse<ResultsHomeResponse<Singer>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<ResultsHomeResponse<Singer>>>,
                        response: Response<ApiResponse<ResultsHomeResponse<Singer>>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null) {
                            list = response.body()?.data!!.listData
                        }
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<ResultsHomeResponse<Singer>>>,
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
            request = { getListSingers() },
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


//    suspend fun getListSongBySinger() : DataResult<MutableList<Song>>{
//        var list = mutableListOf<Song>()
//        return suspendCoroutine {
//            ApiHelper.getInstance().getSongBySinger(accessToken, singerId).enqueue(
//                object : Callback<ApiResponse<ListSongResponse>> {
//                    override fun onResponse(
//                        call: Call<ApiResponse<ListSongResponse>>,
//                        response: Response<ApiResponse<ListSongResponse>>
//                    ) {
//                        if (response.isSuccessful && response.body()?.data != null) {
//                            list = response.body()?.data!!.listMusic
//                        }
//                        it.resume(DataResult.Success(list))
//                    }
//
//                    override fun onFailure(
//                        call: Call<ApiResponse<ListSongResponse>>,
//                        t: Throwable
//                    ) {
//                        it.resume(DataResult.Success(list))
//                    }
//                }
//            )
//        }
//    }
//
//    suspend fun getListSongByCategory() : DataResult<MutableList<Song>> {
//        var list = mutableListOf<Song>()
//        return suspendCoroutine {
//            ApiHelper.getInstance().getSongBySinger(accessToken, categoryId).enqueue(
//                object : Callback<ApiResponse<ListSongResponse>> {
//                    override fun onResponse(
//                        call: Call<ApiResponse<ListSongResponse>>,
//                        response: Response<ApiResponse<ListSongResponse>>
//                    ) {
//                        if (response.isSuccessful && response.body()?.data != null) {
//                            list = response.body()?.data!!.listMusic
//                        }
//                        it.resume(DataResult.Success(list))
//                    }
//
//                    override fun onFailure(
//                        call: Call<ApiResponse<ListSongResponse>>,
//                        t: Throwable
//                    ) {
//                        it.resume(DataResult.Success(list))
//                    }
//                }
//            )
//        }
//    }
//    fun getListBySinger() {
//        executeTask(
//            request = {getListSongBySinger()},
//            onSuccess = {}
//        )
//    }
//    fun getSongByCategory() {
//        executeTask(
//            request = {getListSongByCategory()},
//            onSuccess = {}
//        )
//    }


}