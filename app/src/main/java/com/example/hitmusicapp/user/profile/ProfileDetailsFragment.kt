package com.example.hitmusicapp.user.profile


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentProfileDetailsBinding


class ProfileDetailsFragment : BaseFragment<FragmentProfileDetailsBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    override fun initListener() {
        binding.editProfile.setOnClickListener {
            onItemClickListener?.onReplaceFragment(EditProfileFragment())
            editor.putBoolean("isVisible", false)
            editor.apply()
        }
    }


    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        editor = sharedPreferences.edit()

    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileDetailsBinding {
        return FragmentProfileDetailsBinding.inflate(inflater, container, false)
    }
}