package com.example.hitmusicapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseAdapterRecyclerView
import com.example.hitmusicapp.databinding.ItemCategoryBinding
import com.example.hitmusicapp.entities.Category

class CategoryAdapter : BaseAdapterRecyclerView<Category, ItemCategoryBinding> () {
    override fun bindData(binding: ItemCategoryBinding, item: Category, position: Int) {
        binding.itCategoryName.text = item.name
        Glide.with(binding.imgCategory.context).load(item.image).into(binding.imgCategory)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemCategoryBinding {
        return ItemCategoryBinding.inflate(inflater, parent, false)
    }
}