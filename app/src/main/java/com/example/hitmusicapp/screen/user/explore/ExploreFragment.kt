package com.example.hitmusicapp.screen.user.explore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hitmusicapp.adapters.CategoryAdapter
import com.example.hitmusicapp.adapters.SingerResultAdapter
import com.example.hitmusicapp.adapters.SongResultAdapter
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentExploreBinding
import com.example.hitmusicapp.entities.Category
import com.example.hitmusicapp.entities.Singer
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.screen.play.PlayActivity
import com.example.hitmusicapp.utils.extension.setGridLayoutManager
import com.example.hitmusicapp.utils.extension.setLinearLayoutManager
import com.example.hitmusicapp.viewmodel.HomeViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(HomeViewModel::class.java)
    }

    val sharedPref by lazy {
        requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    val singerAdapter by lazy {
        SingerResultAdapter()
    }

    val songAdapter by lazy {
        SongResultAdapter()
    }

    val categoryAdapter by lazy {
        CategoryAdapter()
    }

    private var keyword = ""

    override fun initView() {
        binding.listResultsSingers.setLinearLayoutManager(
            requireContext(),
            singerAdapter,
            RecyclerView.VERTICAL
        )
        binding.listResultsSongs.setLinearLayoutManager(
            requireContext(),
            songAdapter,
            RecyclerView.VERTICAL
        )
        binding.listResultsCategory.setGridLayoutManager(
            requireContext(), categoryAdapter, RecyclerView.VERTICAL, 2
        )
    }

    override fun initData() {
        binding.searchBar.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel?.keyword = query
                        keyword = query
                    }

                    // Call API
                    viewModel?.getSearchResult()
                    activity?.let {
                        viewModel?.data!!.observe(it) {
//                            if (viewModel?.data != null) {
//                                songAdapter.setDataList(viewModel!!.data.value?.listSong!!)
//                                singerAdapter.setDataList(viewModel!!.data.value?.listSinger!!)
//                                categoryAdapter.setDataList(viewModel!!.data.value?.listCategory!!)
//                            } else {
//                                binding.results.visibility = View.GONE
//                                binding.anmResultFail.visibility = View.VISIBLE
//                            }

                            if (viewModel!!.data.value?.listCategory!!.size == 0 && viewModel!!.data.value?.listSinger!!.size == 0 && viewModel!!.data.value?.listSong!!.size == 0) {
                                binding.results.visibility = View.GONE
//                                binding.anmResultFail.visibility = View.VISIBLE
                            } else  {
                                binding.results.visibility = View.VISIBLE
                                if (viewModel!!.data.value?.listSong!!.size != 0) {
                                    binding.tvSongs.visibility = View.VISIBLE
                                    songAdapter.setDataList(viewModel!!.data.value?.listSong!!)
                                }
                                if (viewModel!!.data.value?.listSinger!!.size != 0) {
                                    binding.tvSingers.visibility = View.VISIBLE
                                    singerAdapter.setDataList(viewModel!!.data.value?.listSinger!!)
                                }
                                if (viewModel!!.data.value?.listCategory!!.size != 0) {
                                    binding.tvCategory.visibility = View.VISIBLE
                                    categoryAdapter.setDataList(viewModel!!.data.value?.listCategory!!)
                                }
                            }



                        }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()) {
                        binding.results.visibility = View.GONE
                        clearData()
                    }
                    return false
                }

            }
        )
    }

    override fun initListener() {
        songAdapter.setOnClickItem { item, position ->
            sharedPref.edit().putString("fragmentSendData", "ExploreFragment").apply()
            val intent = Intent(requireActivity(), PlayActivity::class.java)
            val songId = item?.id
            viewModel?.songPosition?.value = position
            val bundle = Bundle()
            bundle.putString("keyword", keyword)
            bundle.putString("Song_id", songId)
            bundle.putString("keyword", keyword)
            bundle.putInt("Song_position", position)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.filterSinger.setOnCheckedChangeListener { _, _ ->
            if (binding.filterSinger.isChecked) {
                binding.tvSingers.visibility = View.VISIBLE
                binding.listResultsSingers.visibility = View.VISIBLE

                binding.filterAll.isChecked = false

                binding.filterCategory.isChecked = false
                binding.tvCategory.visibility = View.GONE
                binding.listResultsCategory.visibility = View.GONE

                binding.filterSong.isChecked = false
                binding.tvSongs.visibility = View.GONE
                binding.listResultsSongs.visibility = View.GONE
            }
        }

        binding.filterSong.setOnCheckedChangeListener { _, _ ->
            if (binding.filterSong.isChecked) {
                binding.tvSongs.visibility = View.VISIBLE
                binding.listResultsSongs.visibility = View.VISIBLE

                binding.filterAll.isChecked = false

                binding.filterCategory.isChecked = false
                binding.tvCategory.visibility = View.GONE
                binding.listResultsCategory.visibility = View.GONE

                binding.filterSinger.isChecked = false
                binding.tvSingers.visibility = View.GONE
                binding.listResultsSingers.visibility = View.GONE
            }
        }

        binding.filterCategory.setOnCheckedChangeListener { _, _ ->
            if (binding.filterCategory.isChecked) {
                binding.tvCategory.visibility = View.VISIBLE
                binding.listResultsCategory.visibility = View.VISIBLE

                binding.filterSinger.isChecked = false
                binding.tvSingers.visibility = View.GONE
                binding.listResultsSingers.visibility = View.GONE

                binding.filterAll.isChecked = false

                binding.filterSong.isChecked = false
                binding.filterSong.isChecked = false
                binding.tvSongs.visibility = View.GONE
                binding.listResultsSongs.visibility = View.GONE
            }
        }

        binding.filterAll.setOnCheckedChangeListener { _, _ ->
            if (binding.filterAll.isChecked) {

                binding.tvSingers.visibility = View.VISIBLE
                binding.listResultsSingers.visibility = View.VISIBLE

                binding.tvCategory.visibility = View.VISIBLE
                binding.listResultsCategory.visibility = View.VISIBLE

                binding.tvSongs.visibility = View.VISIBLE
                binding.listResultsSongs.visibility = View.VISIBLE

                binding.filterSong.isChecked = false
                binding.filterCategory.isChecked = false
                binding.filterSinger.isChecked = false
            }
        }
    }


    override fun handleEvent() {

    }

    override fun bindData() {
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(inflater, container, false)
    }

    fun clearData() {
        binding.results.visibility = View.GONE
        songAdapter.setDataList(mutableListOf())
        singerAdapter.setDataList(mutableListOf())
        categoryAdapter.setDataList(mutableListOf())
    }

}