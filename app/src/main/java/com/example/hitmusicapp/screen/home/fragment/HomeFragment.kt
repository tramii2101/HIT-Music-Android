package com.example.hitmusicapp.screen.home.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.R
import com.example.hitmusicapp.adapters.CategoryAdapter
import com.example.hitmusicapp.adapters.SingerAdapter
import com.example.hitmusicapp.adapters.SongAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentHomeBinding
import com.example.hitmusicapp.screen.play.PlayActivity
import com.example.hitmusicapp.screen.singer.SingerDetailFragment
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

    private val songAdapter by lazy {
        SongAdapter()
    }

    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun initData() {
        viewModel?.accessToken =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NGMyNzZiNGM1Y2E4ZTMwYTZiMWY3ZGYiLCJpYXQiOjE2OTE1NzU1NDYsImV4cCI6MTY5MTY2MTk0Nn0.U9VPPvsVVc9hj8ZIFQZ9p1_LodnnCWgTpHWY0QbtyR0"
        viewModel?.getData()
        viewModel?.listCategory?.observe(this) {
            categoryAdapter.setDataList(it)
        }

        viewModel?.listSinger?.observe(this) {
            viewModel!!.listSinger.value?.let { it1 -> singerAdapter.setDataList(it1) }
        }

        viewModel?.listSong?.observe(this) {
            viewModel!!.listSong.value?.let { it1 -> songAdapter.setDataList(it1) }
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
            songAdapter,
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
        songAdapter.setOnClickItem { item, position ->
            val intent = Intent(requireActivity(), PlayActivity::class.java)
            val data = item?.id
            val bundle = Bundle()
            bundle.putString("Song", data)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        singerAdapter.setOnClickItem { item, position ->
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.view_pager2, SingerDetailFragment())
                .addToBackStack("Home")
                .commit()
        }

        categoryAdapter.setOnClickItem { item, position ->  }

        binding.ivSearch.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.view_pager2, ExploreFragment())
                .addToBackStack("Home")
                .commit()
        }
    }

    override fun handleEvent() {
        binding.ivSearch.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.view_pager2, ExploreFragment())
                .addToBackStack("Home")
                .commit()
        }
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