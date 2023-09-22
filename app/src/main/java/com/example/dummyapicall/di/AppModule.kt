package com.example.dummyapicall.di

import com.example.dummyapicall.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    private val baseUrl = "https://api.jsonbin.io/v3/b/"
    @Singleton
    @Provides
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
