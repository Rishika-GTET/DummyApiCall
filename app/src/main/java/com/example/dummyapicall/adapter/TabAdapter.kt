package com.example.dummyapicall.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dummyapicall.views.fragments.ChapterPracticeFragment
import com.example.dummyapicall.views.fragments.ChapterVideosFragment

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