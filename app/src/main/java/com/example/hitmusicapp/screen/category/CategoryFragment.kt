package com.example.hitmusicapp.screen.category

import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.adapters.SongInListAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentCategoryBinding
import com.example.hitmusicapp.screen.play.PlayActivity
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.viewmodel.HomeViewModel
import com.example.hitmusicapp.viewmodel.SongViewModel

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    val songViewModel by lazy {
        ViewModelProvider(this)[SongViewModel::class.java]
    }

    val sharedPref by lazy {
        requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }
    val songAdapter by lazy {
        SongInListAdapter()
    }

    override fun initData() {
        val category = viewModel?.categoryPosition?.let { viewModel?.listCategory?.value?.get(it) }
        val token = sharedPref.getString("accessToken", "")
        songViewModel.accessToken = "Bearer $token"
        songViewModel.categoryId = category?.id.toString()
        songViewModel.getSongByCategory()
        songViewModel.listSongByCategory.observe(this){
            songViewModel.listSongByCategory.value?.let { it1 -> songAdapter.setDataList(it1) }
        }
    }

    override fun initView() {
        val category = viewModel?.categoryPosition?.let { viewModel?.listCategory?.value?.get(it) }
        binding.tvCategory.text = category?.name
        binding.rcvListSong.setLinearLayoutManager(requireContext(), songAdapter, RecyclerView.VERTICAL)
    }


    override fun initListener() {
        songAdapter.setOnClickItem { item, position ->
            val intent = Intent(requireActivity(), PlayActivity::class.java)
            val songId = item?.id
            val singerId = viewModel?.singerId
            val bundle = Bundle()
            bundle.putString("Song_of_category_id", songId)
            bundle.putInt("Song_of_category_category", position)
            bundle.putString("Category_id", singerId)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "forgot_password",
                FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {

        }
    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(inflater, container, false)
    }
}