package com.example.dummyapicall

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragment: Fragment, private var totalTabs: Int) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return totalTabs
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChapterVideosFragment()
            }

            1 -> {
                ChapterPracticeFragment()
            }
            else -> ChapterVideosFragment()
        }
    }
}  