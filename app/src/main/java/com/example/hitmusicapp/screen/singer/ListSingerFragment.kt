package com.example.hitmusicapp.screen.singer

import android.app.FragmentManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.adapters.SingerAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentListSingerBinding
import com.example.hitmusicapp.screen.category.CategoryFragment
import com.example.hitmusicapp.utils.extension.setGridLayoutManager
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.viewmodel.HomeViewModel

class ListSingerFragment : BaseFragment<FragmentListSingerBinding>() {

    private val singerAdapter by lazy {
        SingerAdapter()
    }

    private val sharedPref by lazy {
        requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    private val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    override fun initData() {
        viewModel?.listSinger?.value?.let { singerAdapter.setDataList(it) }
    }


    override fun initView() {
        binding.rcvSinger.setGridLayoutManager(
            requireContext(),
            singerAdapter,
            RecyclerView.VERTICAL,
            2
        )
    }

    override fun initListener() {
        singerAdapter.setOnClickItem { _, position ->
            viewModel?.singerPosition = position
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, SingerDetailFragment())
                .addToBackStack("Home")
                .commit()
            (activity as MainActivity).gone()
        }

        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "Home",
                FragmentManager.POP_BACK_STACK_INCLUSIVE)
            (activity as MainActivity).visible()
        }

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListSingerBinding {
        return FragmentListSingerBinding.inflate(inflater, container, false)
    }

}