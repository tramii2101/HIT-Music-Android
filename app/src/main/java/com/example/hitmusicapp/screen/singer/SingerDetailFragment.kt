package com.example.hitmusicapp.screen.singer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hitmusicapp.adapters.SongInListAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSingerDetailBinding
import com.example.hitmusicapp.screen.play.PlayActivity
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.viewmodel.HomeViewModel
import com.example.hitmusicapp.viewmodel.SongViewModel
import kotlin.math.sin

class SingerDetailFragment : BaseFragment<FragmentSingerDetailBinding>() {

    val viewModel by lazy {
       activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    val sharedPref by lazy {
        requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    val songViewModel by lazy {
        ViewModelProvider(this)[SongViewModel::class.java]
    }

    val songAdapter by lazy {
        SongInListAdapter()
    }

    override fun initData() {
        val singer = viewModel?.singerPosition?.let { viewModel?.listSinger?.value!![it] }
        val token = sharedPref.getString("accessToken", "")
        songViewModel.singerId = singer?.id.toString()
        songViewModel.accessToken = "Bearer $token"
        songViewModel.getSongBySinger()
        songViewModel.listSong.observe(this) {
            songViewModel.listSong.value?.let { it1 -> songAdapter.setDataList(it1) }
        }
    }

    override fun initView() {
        val singer = viewModel?.singerPosition?.let { viewModel?.listSinger?.value!![it] }
        Glide.with(binding.imgSinger.context).load(singer?.avatar).into(binding.imgSinger)
        binding.tvSinger.text = singer?.fullname
        binding.rcvListSongs.setLinearLayoutManager(requireContext(), songAdapter, RecyclerView.VERTICAL)
    }

    override fun initListener() {
        songAdapter.setOnClickItem { item, position ->
            val intent = Intent(requireActivity(), PlayActivity::class.java)
            val songId = item?.id
            val singerId = viewModel?.singerId
            val bundle = Bundle()
            bundle.putString("Song_of_singer_id", songId)
            bundle.putInt("Song_of_singer_position", position)
            bundle.putString("Singer_id", singerId)
            intent.putExtras(bundle)
            startActivity(intent)
        }
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