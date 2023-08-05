package com.example.hitmusicapp

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.hitmusicapp.adapters.ViewPagerAdapter
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initListener() {

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (binding.viewPager2.currentItem) {
                    0 -> {
                        binding.btnNav.menu.findItem(R.id.action_home).isChecked = true
                        toast("Home")
                    }
                    1 -> {
                        binding.btnNav.menu.findItem(R.id.action_explore).isChecked = true
                        toast("Explore")
                    }
                    2 -> {
                        binding.btnNav.menu.findItem(R.id.action_library).isChecked = true
                        toast("Library")
                    }
                    3 -> {
                        binding.btnNav.menu.findItem(R.id.action_profile).isChecked = true
                        toast("Profile")
                    }
                }
            }
        })

        binding.btnNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    binding.viewPager2.currentItem = 0
                    toast("Home")
                    true
                }
                R.id.action_explore -> {
                    binding.viewPager2.currentItem = 1
                    toast("Explore")
                    true
                }
                R.id.action_library -> {
                    binding.viewPager2.currentItem = 2
                    toast("Library")
                    true
                }
                R.id.action_profile -> {
                    binding.viewPager2.currentItem = 3
                    toast("Profile")
                    true
                }
                else -> false
            }
        }
    }


    override fun initData() {

    }

    override fun initView() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun Context.toast(s: String) {
        Toast.makeText(this@MainActivity, s, Toast.LENGTH_SHORT).show()
    }
}