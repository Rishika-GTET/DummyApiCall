package com.example.dummyapicall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    // Initialize Retrofit for subject data
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}