package com.example.hitmusicapp.screen.user.home

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.adapters.CategoryAdapter
import com.example.hitmusicapp.adapters.SingerAdapter
import com.example.hitmusicapp.adapters.SongAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentHomeBinding
import com.example.hitmusicapp.screen.category.CategoryFragment
import com.example.hitmusicapp.screen.play.PlayActivity
import com.example.hitmusicapp.screen.singer.SingerDetailFragment
import com.example.hitmusicapp.screen.user.explore.ExploreFragment
import com.example.hitmusicapp.screen.user.profile.OnItemClickListener
import com.example.hitmusicapp.utils.extension.setGridLayoutManager
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.viewmodel.HomeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    val sharedPreferences by lazy {
        requireActivity().getSharedPreferences("home_pref", Context.MODE_PRIVATE)
    }

    val sharedPref by lazy {
        requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
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
        val token = sharedPref.getString("accessToken", "")
        viewModel?.accessToken =
            "Bearer $token"
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
//        viewModel?.loading?.observe(this) {
//            if (it) {
//                dialog?.start(
//                    false
//                )
//            } else {
//                dialog?.dismiss()
//            }
//        }

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
            val songId = item?.id
            val songPosition = position
            val bundle = Bundle()
            bundle.putString("Song_id", songId)
            bundle.putInt("Song_position", position)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        singerAdapter.setOnClickItem { item, position ->
            viewModel?.singerPosition = position
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, SingerDetailFragment())
                .addToBackStack("Home")
                .commit()
            (activity as MainActivity).gone()
        }

        categoryAdapter.setOnClickItem { item, position ->
            viewModel?.categoryPosition = position
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, CategoryFragment())
                .addToBackStack("Home")
                .commit()
            (activity as MainActivity).gone()
        }

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