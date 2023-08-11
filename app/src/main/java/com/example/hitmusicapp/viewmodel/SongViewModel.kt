package com.example.hitmusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.base.DataResult
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.ListSongResponse
import com.example.hitmusicapp.entity.SongResponse
import com.example.hitmusicapp.retrofit.ApiHelper
import com.example.hitmusicapp.retrofit.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SongViewModel : BaseViewModel() {

    private val _song = MutableLiveData<SongResponse>()
    val song: LiveData<SongResponse>
        get() = _song

    private val _listSongByCategory = MutableLiveData<MutableList<Song>>()
    val listSongByCategory: LiveData<MutableList<Song>>
        get() = _listSongByCategory

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _listSong = MutableLiveData<MutableList<Song>>()
    val listSong: LiveData<MutableList<Song>>
        get() = _listSong


    var accessToken = ""
    var singerId = ""
    var categoryId = ""
    var songId = ""

    suspend fun getSongById(): DataResult<SongResponse> {
        var song = SongResponse()
        return suspendCoroutine {
            ApiHelper.getInstance().getSongById(accessToken, songId).enqueue(
                object : Callback<ApiResponse<SongResponse>> {
                    override fun onResponse(
                        call: Call<ApiResponse<SongResponse>>,
                        response: Response<ApiResponse<SongResponse>>
                    ) {
                        if (response.isSuccessful && response.body()?.data != null) {
                            song = response.body()?.data!!
                        }
                        it.resume(DataResult.Success(song))
                    }

                    override fun onFailure(call: Call<ApiResponse<SongResponse>>, t: Throwable) {
                        it.resume(DataResult.Success(song))
                    }

                }
            )
        }
    }

    fun getSong() {
        executeTask(
            request = { getSongById() },
            onSuccess = {
                _song.value = it
            }
        )
    }

    suspend fun getListSongBySinger(): DataResult<MutableList<Song>> {
        var list = mutableListOf<Song>()
        return suspendCoroutine {
            ApiHelper.getInstance().getSongBySinger(accessToken, singerId).enqueue(
                object : Callback<ApiResponse<ListSongResponse>> {
                    override fun onResponse(
                        call: Call<ApiResponse<ListSongResponse>>,
                        response: Response<ApiResponse<ListSongResponse>>
                    ) {
                        if (response.isSuccessful && response.body()?.data?.listMusic != null) {
                            list = response.body()?.data?.listMusic!!
                        }
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<ListSongResponse>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                    }

                }
            )
        }
    }

    fun getSongBySinger() {
        executeTask(
            request = { getListSongBySinger() },
            onSuccess = {
                _listSong.value = it
            }
        )
    }

    suspend fun addSongToFavourite(): DataResult<String> {
        var message = ""
        return suspendCoroutine {
            ApiHelper.getInstance().addToFavourite(accessToken, mapOf("musicId" to songId)).enqueue(
                object : Callback<ApiResponse<String>> {
                    override fun onResponse(
                        call: Call<ApiResponse<String>>,
                        response: Response<ApiResponse<String>>
                    ) {
                        if (response.isSuccessful) {
                            message = response.message()
                        }
                        it.resume(DataResult.Success(message))
                    }

                    override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
                        message = "Unsuccessful"
                        it.resume(DataResult.Success(message))
                    }

                }
            )
        }
    }

    fun addToFavourite() {
        executeTask(
            request = {addSongToFavourite()},
            onSuccess = {
                _message.value = it
            },
            onError = {}
        )
    }

    suspend fun getSongInCategory() : DataResult<MutableList<Song>> {
        var list = mutableListOf<Song>()
        return suspendCoroutine {
            ApiHelper.getInstance().getSongInCategory(accessToken, categoryId).enqueue(
                object : Callback<ApiResponse<ListSongResponse>> {
                    override fun onResponse(
                        call: Call<ApiResponse<ListSongResponse>>,
                        response: Response<ApiResponse<ListSongResponse>>
                    ) {
                        if (response.isSuccessful && response.body()?.data?.listMusic != null) {
                            list = response.body()?.data?.listMusic!!
                        }
                        it.resume(DataResult.Success(list))
                    }

                    override fun onFailure(
                        call: Call<ApiResponse<ListSongResponse>>,
                        t: Throwable
                    ) {
                        it.resume(DataResult.Success(list))
                    }
                }
            )
        }
    }

    fun getSongByCategory() {
        executeTask(
            request = {getSongInCategory()},
            onSuccess = {
                _listSongByCategory.value = it
            },
            onError = {}
        )
    }

}