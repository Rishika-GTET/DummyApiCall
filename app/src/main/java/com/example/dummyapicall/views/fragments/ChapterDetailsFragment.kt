package com.example.dummyapicall.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dummyapicall.R
import com.example.dummyapicall.adapter.TabAdapter
import com.example.dummyapicall.databinding.FragmentChapterDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChapterDetailsFragment:Fragment() {
    private lateinit var binding: FragmentChapterDetailsBinding
    private val args: ChapterDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_chapter_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chapterHeading.text=args.title
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = TabAdapter(this, binding.tabLayout.tabCount)
        binding.viewPager.adapter=adapter




        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
              //  TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
              //  TODO("Not yet implemented")
            }
        })

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.tab_icon1)
                    tab.text = "Videos"
                }
                1 -> {
                    tab.text = "Practice"
                    tab.setIcon(R.drawable.tab_icon2)
                }
            }
        }.attach()

    }

}
