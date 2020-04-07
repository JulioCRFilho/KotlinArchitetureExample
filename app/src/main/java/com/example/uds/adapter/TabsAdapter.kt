package com.example.uds.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.uds.fragment.DoneSchedulesFragment
import com.example.uds.fragment.OpenSchedulesFragment
import com.example.uds.viewModel.HomeViewModel
import com.google.android.material.appbar.AppBarLayout

class TabsAdapter(fm: FragmentManager, private val vm: HomeViewModel) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OpenSchedulesFragment(vm)
            else -> DoneSchedulesFragment(vm)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Abertas"
            else -> "Finalizadas"
        }
    }

    override fun getCount(): Int = 2

}