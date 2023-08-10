package com.example.hitmusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitmusicapp.base.BaseViewModel
import com.example.hitmusicapp.entities.Category

class CategoryViewModel : BaseViewModel() {
    private val _listCategory = MutableLiveData<MutableList<Category>>()
    val listCategory: LiveData<MutableList<Category>>
        get() = _listCategory

    private val _category = MutableLiveData<Category>()
    val category : LiveData<Category>
    get() = _category

    var accessToken = ""


}