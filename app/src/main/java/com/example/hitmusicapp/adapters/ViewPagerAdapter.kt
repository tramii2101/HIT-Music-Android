package com.example.hitmusicapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hitmusicapp.screen.home.fragment.ExploreFragment
import com.example.hitmusicapp.screen.home.fragment.HomeFragment
import com.example.hitmusicapp.screen.home.fragment.LibraryFragment
import com.example.hitmusicapp.screen.home.fragment.ProfileFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4;
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                ExploreFragment()
            }
            2 -> {
                LibraryFragment()
            }
            else -> {
                ProfileFragment()
            }

        }
    }
}