package com.bignerdranch.android.bullsandcowsapp.rules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RulesPageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                FirstRulesFragment()
            }
            1 -> {
                SecondRulesFragment()
            }
            else -> {
                FirstRulesFragment()
            }
        }
    }

}