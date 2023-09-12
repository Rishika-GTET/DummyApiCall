package com.example.dummyapicall
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapicall.databinding.FragmentVideosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideosFragment : Fragment() {
    private lateinit var binding:FragmentVideosBinding
    lateinit var adapter: VideoAdapter
    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_videos,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.recyclerViewVideos.layoutManager = LinearLayoutManager(requireContext())

        // Initialize Retrofit for subject data
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//
//        // Create an instance of the SubjectApiService interface
        val apiService = retrofit.create(ApiService::class.java)

        // Fetch subjects from the API
        val call = apiService.getVideos()
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) = if (response.isSuccessful) {
                val videoResponse = response.body()
                val subject = videoResponse?.record
                val videos = subject ?: emptyList()
                println("Chapters $videos")
                adapter= VideoAdapter(videos)
                binding.recyclerViewVideos.adapter = adapter

            } else {
                // Handle the error
            }

            override fun onFailure(call: Call<VideoResponse>, t:Throwable) {
                t.printStackTrace()
            }
        })
    }


}
