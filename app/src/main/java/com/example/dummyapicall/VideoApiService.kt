package com.example.dummyapicall

import retrofit2.Call
import retrofit2.http.GET


interface VideoApiService {
    @GET("64fac6efe4033326cbd3d88c")
    fun getVideos(): Call<VideoResponse>
}