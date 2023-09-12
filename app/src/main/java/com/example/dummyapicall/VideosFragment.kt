package com.example.dummyapicall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dummyapicall.Retrofit.retrofitService
import com.example.dummyapicall.databinding.FragmentVideosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideosFragment : Fragment() {
    private lateinit var binding: FragmentVideosBinding
    lateinit var adapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        // Fetch subjects from the API
        val call = retrofitService.getVideos()
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) = if (response.isSuccessful) {
                val videoResponse = response.body()
                val subject = videoResponse?.record
                val videos = subject ?: emptyList()
                println("Chapters $videos")
                adapter = VideoAdapter(videos)
                binding.recyclerViewVideos.adapter = adapter

            } else {
                // Handle the error
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}
