package com.example.dummyapicall

import retrofit2.Call
import retrofit2.http.GET

//interface ProductApiService {
//    @GET("products")
//    fun getProducts(): Call<ProductResponse>
//}

interface SubjectApiService {
    @GET("64fac54ae4033326cbd3d7e3")
    fun getSubject(): Call<SubjectResponse>
}