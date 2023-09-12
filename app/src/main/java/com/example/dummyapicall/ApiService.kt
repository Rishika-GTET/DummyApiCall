package com.example.dummyapicall

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("64fac6efe4033326cbd3d88c")
    fun getVideos(): Call<VideoResponse>

    @GET("64fac54ae4033326cbd3d7e3")
    fun getSubject(): Call<SubjectResponse>
}