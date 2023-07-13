package com.example.hitmusicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemSingerBinding
import com.example.hitmusicapp.entities.Singer

class SingerAdapter : BaseAdapterRecyclerView<Singer, ItemSingerBinding>(){

    override fun bindData(binding: ItemSingerBinding, item: Singer, position: Int) {
//        binding.tvSinger.text = item.fullname
//        if (item.avatar != null) {
//            Glide.with(context).load(item.avatar).into(binding.imgArtist)
//        }
        binding.tvSinger.text = item.fullname
        Glide.with(binding.imgArtist.context).load(item.avatar).into(binding.imgArtist)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemSingerBinding {
        return ItemSingerBinding.inflate(inflater, parent, false)
    }
}