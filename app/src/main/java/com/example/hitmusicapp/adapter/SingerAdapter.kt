package com.example.hitmusicapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemSingerBinding
import com.example.hitmusicapp.entities.Singer

class SingerAdapter : BaseAdapterRecyclerView<Singer, ItemSingerBinding>(){

    override fun bindData(binding: ItemSingerBinding, item: Singer, position: Int) {
        val context: Context = binding.root.context
        binding.tvSinger.text = item.fullName
        if (item.avt != null) {
            Glide.with(context).load(item.avt).into(binding.imgArtist)
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemSingerBinding {
        return ItemSingerBinding.inflate(inflater, parent, false)
    }
}