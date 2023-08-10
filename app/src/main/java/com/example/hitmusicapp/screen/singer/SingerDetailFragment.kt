package com.example.hitmusicapp.screen.singer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.adapters.SongAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSingerDetailBinding
import com.example.hitmusicapp.viewmodel.HomeViewModel
import com.example.hitmusicapp.viewmodel.SongViewModel

class SingerDetailFragment : BaseFragment<FragmentSingerDetailBinding>() {

    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    val songAdapter by lazy {
        SongAdapter()
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }


    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingerDetailBinding {
        return FragmentSingerDetailBinding.inflate(inflater, container, false)
    }

}