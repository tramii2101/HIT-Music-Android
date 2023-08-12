package com.example.hitmusicapp

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hitmusicapp.adapters.ViewPagerAdapter
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityMainBinding
import com.example.hitmusicapp.screen.user.profile.LogoutBottomSheetDialog
import com.example.hitmusicapp.screen.user.profile.OnItemClickListener
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>(), OnItemClickListener {
    private var isGone = false
    private var fragmentReplaced = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor


    override fun onReplaceFragment(fragment: Fragment) {
        replace(fragment)
        editor.putBoolean("fragmentReplaced", true)
        editor.apply()
        gone()

    }

    private fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main, fragment).addToBackStack(null)
            .commit()
    }

    fun gone() {
        binding.btnNav.visibility = View.GONE
        binding.viewPager2.visibility = View.GONE
        isGone = true

    }

    fun visible() {
        if (isGone) {
            binding.btnNav.visibility = View.VISIBLE
            binding.viewPager2.visibility = View.VISIBLE
            isGone = false

        }
    }

    override fun onBackPressed() {

        val isVisible = sharedPreferences.getBoolean("isVisible", false)
        if (isVisible) {
            visible()
        } else {
            gone()
        }
        editor.putBoolean("isVisible", true)
        editor.putBoolean("fragmentReplaced", false)
        editor.apply()
        //code doesn't exit the app when user click back
        val logoutBottomSheetDialog = LogoutBottomSheetDialog()
        logoutBottomSheetDialog.show(supportFragmentManager, "")
        logoutBottomSheetDialog.setOnClickListener(object :
            LogoutBottomSheetDialog.OnClickListener {
            override fun onYesClick() {
                //put lai du lieu login
                back()
            }

            override fun onNoClick() {
                logoutBottomSheetDialog.dismiss()
            }
        })

    }

    fun back() {
        super.onBackPressed()
    }

    override fun initListener() {

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.setOnClickListener(this)
        binding.viewPager2.adapter = adapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (binding.viewPager2.currentItem) {
                    0 -> {
                        binding.btnNav.menu.findItem(R.id.action_home).isChecked = true
                    }
                    1 -> {
                        binding.btnNav.menu.findItem(R.id.action_explore).isChecked = true
                    }
                    2 -> {
                        binding.btnNav.menu.findItem(R.id.action_library).isChecked = true
                    }
                    3 -> {
                        binding.btnNav.menu.findItem(R.id.action_profile).isChecked = true
                    }
                }
            }
        })

        binding.btnNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    binding.viewPager2.currentItem = 0
                    true
                }
                R.id.action_explore -> {
                    binding.viewPager2.currentItem = 1
                    true
                }
                R.id.action_library -> {
                    binding.viewPager2.currentItem = 2
                    true
                }
                R.id.action_profile -> {
                    binding.viewPager2.currentItem = 3
                    true
                }
                else -> false
            }
        }
    }

    fun updateLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        recreate()

    }

    override fun initData() {

    }

    override fun initView() {
        sharedPreferences =
            this?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) ?: sharedPreferences
        editor = sharedPreferences.edit()
        fragmentReplaced = sharedPreferences.getBoolean("fragmentReplaced", false)
        if (fragmentReplaced) {
            gone()
        } else {
            visible()
        }
    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


}