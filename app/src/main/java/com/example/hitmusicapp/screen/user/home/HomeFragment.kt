package com.example.hitmusicapp.screen.user.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.adapters.PopularSongAdapter
import com.example.hitmusicapp.adapters.SingerAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentHomeBinding
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager

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
        binding.recyclerSinger.setLinearLayoutManager(
            requireContext(),
            singerAdapter,
            RecyclerView.HORIZONTAL
        )
        binding.recyclerTrend.setLinearLayoutManager(
            requireContext(),
            popularSongAdapter,
            RecyclerView.HORIZONTAL
        )
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