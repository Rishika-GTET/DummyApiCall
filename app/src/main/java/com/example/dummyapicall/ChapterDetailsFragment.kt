package com.example.dummyapicall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dummyapicall.databinding.FragmentChapterDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChapterDetailsFragment:Fragment() {
    private lateinit var binding: FragmentChapterDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chapter_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = requireParentFragment().fragmentManager?.let { TabAdapter(this, binding.tabLayout.tabCount) }
        binding.viewPager.adapter=adapter

        // Fetch subjects from the API
        val call = Retrofit.retrofitService.getVideos()
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) {
                if (response.isSuccessful) {
                    val videoResponse = response.body()
                    val subject = videoResponse?.record
                    val videos = subject ?: emptyList()
                    println("Chapters $videos")


                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<VideoResponse>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })


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
                    tab.text = "Videos"
                }
                1 -> {
                    tab.text = "Practice"
                }
            }
        }.attach()

    }

}
