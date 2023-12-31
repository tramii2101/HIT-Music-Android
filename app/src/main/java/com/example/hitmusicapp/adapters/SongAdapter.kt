package com.example.hitmusicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemSongInHomeBinding
import com.example.hitmusicapp.entities.Song

class SongAdapter : BaseAdapterRecyclerView<Song, ItemSongInHomeBinding>() {
    override fun bindData(binding: ItemSongInHomeBinding, item: Song, position: Int) {
        binding.tvSong.text = item.title
        Glide.with(binding.root.context).load(item.image).into(binding.imgBackground)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemSongInHomeBinding {
        return ItemSongInHomeBinding.inflate(inflater, parent, false)
    }

}