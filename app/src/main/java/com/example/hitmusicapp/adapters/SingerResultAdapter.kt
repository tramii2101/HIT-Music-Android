package com.example.hitmusicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemResultSingerBinding
import com.example.hitmusicapp.entities.Singer

class SingerResultAdapter : BaseAdapterRecyclerView<Singer, ItemResultSingerBinding>(){
    override fun bindData(binding: ItemResultSingerBinding, item: Singer, position: Int) {
        binding.tvSinger.text = item.fullname
        Glide.with(binding.imgSinger.context).load(item.avatar).into(binding.imgSinger)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemResultSingerBinding {
        return ItemResultSingerBinding.inflate(inflater, parent, false)
    }
}