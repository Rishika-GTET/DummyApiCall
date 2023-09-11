package com.example.dummyapicall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideosActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var videoAdapter: VideoAdapter
    lateinit var videoList: ArrayList<VideoItem>
    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        recyclerView = findViewById(R.id.recycler_view_videos)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        videoList = ArrayList()
//
//        videoList.add(
//            VideoItem(
//                R.drawable.baseline_error_24,
//                "title 1",
//                "description 1",
//                videoTotalDuration = "5.1"
//            )
//        )
//        videoList.add(
//            VideoItem(
//                R.drawable.baseline_error_24,
//                "title 2",
//                "description 2",
//                videoTotalDuration = "5.1"
//            )
//        )
//        videoList.add(
//            VideoItem(
//                R.drawable.baseline_error_24,
//                "title 3",
//                "description 3",
//                videoTotalDuration = "5.1"
//            )
//        )
//        videoList.add(
//            VideoItem(
//                R.drawable.baseline_error_24,
//                "title 4",
//                "description 4",
//                videoTotalDuration = "5.1"
//            )
//        )
//        videoList.add(
//            VideoItem(
//                R.drawable.baseline_error_24,
//                "title 5",
//                "description 5",
//                videoTotalDuration = "5.1"
//            )
//        )
//        videoAdapter = VideoAdapter(videoList)
//        recyclerView.adapter = videoAdapter // Set


        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //        // Create an instance of the Videoapiservice interface
        val apiService = retrofit.create(VideoApiService::class.java)

        // Fetch videos from the API
        val call = apiService.getVideos()
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) {
                if (response.isSuccessful) {
                    val videoResponse = response.body()
                    val videos = videoResponse?.record ?: emptyList()
                    println("Videos ${videos.toString()}")
                    // Pass the subjects and chapters to the adapter
                    videoAdapter = VideoAdapter(videos)
                    recyclerView.adapter = videoAdapter
                } else {
                    // Handle the error
                    println("Error while calling API ${response.toString()}")
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}