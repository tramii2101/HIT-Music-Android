package com.example.hitmusicapp.user.profile


import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var switch: Switch
    private lateinit var editor: SharedPreferences.Editor
    var nightMode: Boolean = true

    override fun initListener() {
        sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        nightMode = sharedPreferences.getBoolean("nightMode", false)
        switch = binding.sw
        editor = sharedPreferences.edit()

        if (nightMode) {


        } else {


            switch.isChecked = true
        }
        editor.apply()

        switch.setOnClickListener {
            nightMode = sharedPreferences.getBoolean("nightMode", false)
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("nightMode", false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("nightMode", true)
            }
            editor.apply()
        }
        binding.logoutProfile.setOnClickListener {

            LogoutBottomSheetDialog().also { dialog ->

                dialog.show(parentFragmentManager, "")

            }

        }
        binding.languageProfile.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.main, LanguageFragment()).commit()
        }
    }


    override fun initView() {

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
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }
}