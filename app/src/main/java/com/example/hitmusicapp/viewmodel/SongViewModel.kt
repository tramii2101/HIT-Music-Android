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

    private val _listSong = MutableLiveData<MutableList<Song>>()
    val listSong: LiveData<MutableList<Song>>
        get() = _listSong

    var accessToken = ""
    var singerId = ""
    var categoryId = ""
    var songId = ""

    suspend fun getSongById() : DataResult<SongResponse> {
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
            request = {getSongById()},
            onSuccess = {
                _song.value = it
            }
        )
    }

}