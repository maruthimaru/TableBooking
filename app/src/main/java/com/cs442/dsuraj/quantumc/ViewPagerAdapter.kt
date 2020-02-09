package com.cs442.dsuraj.quantumc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            return Fragment1()
        } else if (position == 1) {
            return Fragment2()
        } else if (position == 2) {
           return Fragment3()
        } else {
            return Fragment4()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}