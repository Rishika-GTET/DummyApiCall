package com.example.dummyapicall.viewmodels
import androidx.lifecycle.ViewModel
import com.example.dummyapicall.models.VideoItem
import com.example.dummyapicall.models.VideoResponse
import com.example.dummyapicall.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ChapterVideosViewModel :ViewModel(){

    @Inject
    lateinit var apiService: ApiService

    var videos:MutableStateFlow<List<VideoItem>> = MutableStateFlow(emptyList())
    init {
        chapterVideos()
    }

    private fun chapterVideos(){
        val call = apiService.getVideos()
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) {
                if (response.isSuccessful) {
                    val videoResponse = response.body()
                    val subject = videoResponse?.record
                    videos.value = subject ?: emptyList()
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<VideoResponse>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })

    }
}