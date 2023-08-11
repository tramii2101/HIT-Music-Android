package com.example.hitmusicapp.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.base.BaseViewHolder
import com.example.hitmusicapp.databinding.ItemSongBinding
import com.example.hitmusicapp.entities.Song

class SongInListAdapter : BaseAdapterRecyclerView<Song, ItemSongBinding>() {

    override fun bindData(binding: ItemSongBinding, item: Song, position: Int) {
        Glide.with(binding.imgSong.context).load(item.image).into(binding.imgSong)
        binding.tvSongName.text = item.title
        binding.tvSinger.text = item.singer
    }

    override fun inflateViewBinding(inflater: LayoutInflater, parent: ViewGroup): ItemSongBinding {
        return ItemSongBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemSongBinding>, position: Int) {

        super.onBindViewHolder(holder, position)
        holder.binding.imgFavourite.setOnClickListener {
            val dialog : AlertDialog

//            Toast.makeText(binding?.imgFavourite?.context, "Added to favourite", Toast.LENGTH_SHORT).show()
        }


    }


}