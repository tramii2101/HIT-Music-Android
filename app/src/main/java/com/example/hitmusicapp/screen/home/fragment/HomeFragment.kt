package com.example.hitmusicapp.screen.home.fragment

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.adapters.CategoryAdapter
import com.example.hitmusicapp.adapters.PopularSongAdapter
import com.example.hitmusicapp.adapters.SingerAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentHomeBinding
import com.example.hitmusicapp.utils.extension.setGridLayoutManager
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.utils.extension.start
import com.example.hitmusicapp.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    private val dialog by lazy { context?.let { Dialog(it) } }

    private val singerAdapter by lazy {
        SingerAdapter()
    }

    private val popularSongAdapter by lazy {
        PopularSongAdapter()
    }

    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun initData() {
        viewModel?.accessToken =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NGMyNzZiNGM1Y2E4ZTMwYTZiMWY3ZGYiLCJpYXQiOjE2OTE0ODcyNjEsImV4cCI6MTY5MTU3MzY2MX0.icnjllDZntuoGSMEimow2NPblgFUBIgaz8SGCw9Mk9c"
        viewModel?.getData()
        viewModel?.listCategory?.observe(this) {
            categoryAdapter.setDataList(it)
        }

        viewModel?.listSinger?.observe(this) {
            viewModel!!.listSinger.value?.let { it1 -> singerAdapter.setDataList(it1) }
        }

        viewModel?.listSong?.observe(this) {
            viewModel!!.listSong.value?.let { it1 -> popularSongAdapter.setDataList(it1) }
        }

    }
    override fun initView() {
        viewModel?.loading?.observe(this) {
            if (it) {
                dialog?.start(
                    false
                )
            } else {
                dialog?.dismiss()
            }
        }

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

        binding.recyclerCategory.setGridLayoutManager(
            requireContext(),
            categoryAdapter,
            RecyclerView.VERTICAL,
            2
        )
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
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

}