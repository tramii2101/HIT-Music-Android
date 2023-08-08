package com.example.hitmusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.entities.Song

class SongViewModel : BaseViewModel() {

    private val _song = MutableLiveData<Song>()
    val song: LiveData<Song>
        get() = _song

    var accessToken = ""




}