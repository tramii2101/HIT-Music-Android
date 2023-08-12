package com.example.hitmusicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemSongResultBinding
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.SongResultSearch

class SongResultAdapter : BaseAdapterRecyclerView<SongResultSearch, ItemSongResultBinding>(){
    override fun bindData(binding: ItemSongResultBinding, item: SongResultSearch, position: Int) {
        binding.tvSongName.text = item.title
        binding.tvSinger.text = item.singer?.fullname
        Glide.with(binding.imgSong.context).load(item.image).into(binding.imgSong)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemSongResultBinding {
        return ItemSongResultBinding.inflate(inflater, parent, false)
    }
}