package com.example.uds.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.uds.fragment.DoneSchedulesFragment
import com.example.uds.fragment.OpenSchedulesFragment

class TabsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OpenSchedulesFragment()
            else -> DoneSchedulesFragment()
        }
    }

    override fun getCount(): Int = 2

}