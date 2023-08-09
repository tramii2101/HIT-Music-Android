package com.example.hitmusicapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hitmusicapp.screen.user.explore.ExploreFragment
import com.example.hitmusicapp.screen.user.home.HomeFragment
import com.example.hitmusicapp.screen.user.library.LibraryFragment
import com.example.hitmusicapp.screen.user.profile.OnItemClickListener
import com.example.hitmusicapp.screen.user.profile.ProfileFragment
import com.example.hitmusicapp.screen.user.profile.onItemClickListener


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

    fun setOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}