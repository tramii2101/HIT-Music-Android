package com.example.hitmusicapp.screen.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hitmusicapp.adapter.PopularSongAdapter
import com.example.hitmusicapp.adapter.SingerAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val singerAdapter by lazy {
        SingerAdapter()
    }

    private val popularSongAdapter by lazy {
        PopularSongAdapter()
    }

    override fun initListener() {

    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

}