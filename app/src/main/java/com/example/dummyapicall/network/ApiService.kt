package com.example.dummyapicall.network

import com.example.dummyapicall.SubjectResponse
import com.example.dummyapicall.models.PracticeListItem
import com.example.dummyapicall.models.SubjectListResponse
import com.example.dummyapicall.models.VideoResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("64fac6efe4033326cbd3d88c")
    fun getVideos(): Call<VideoResponse>

    @GET("64fac54ae4033326cbd3d7e3")
    fun getMathematicsSubjectDetails(): Call<SubjectResponse>


    @GET("64fac570d972192679c010bd")
    fun getPhysicsSubjectDetails(): Call<SubjectResponse>

    @GET("64fac58be4033326cbd3d7f4")
    fun getChemistrySubjectDetails(): Call<SubjectResponse>


    @GET("64fac25ee4033326cbd3d6d2")
    fun getSubjectList(): Call<SubjectListResponse>

    @GET("64fac7e4d972192679c011ad")
    fun getPracticeList(): Call<PracticeListItem>
}
