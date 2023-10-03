package com.example.dummyapicall.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dummyapicall.Chapter
import com.example.dummyapicall.SubjectResponse
import com.example.dummyapicall.models.Record
import com.example.dummyapicall.models.SubjectListResponse
import com.example.dummyapicall.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class ChapterViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var chapterList: MutableStateFlow<List<Chapter>> = MutableStateFlow(emptyList())
    var subjectImage: MutableStateFlow<String> = MutableStateFlow("")
    var subjectName: MutableStateFlow<String> = MutableStateFlow("")
    var subjectList: MutableStateFlow<List<Record>> = MutableStateFlow(emptyList())

    init {
            getSubjectList()
    }

    private fun getSubjectList() {
        val call = apiService.getSubjectList()
        call.enqueue(object : Callback<SubjectListResponse?> {
            override fun onResponse(
                call: Call<SubjectListResponse?>,
                response: Response<SubjectListResponse?>
            ) {
                if (response.isSuccessful) {
                    subjectList.value = response.body()?.record!!
                    subjectImage.value = subjectList.value.getOrNull(0)?.subjectImage ?: ""
                    subjectName.value = subjectList.value.getOrNull(0)?.subjectName ?: ""
                    getSubjectChapters(subjectName.value)
                }
            }

            override fun onFailure(call: Call<SubjectListResponse?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getSubjectChapters(subjectName: String) {
        val serviceCall = when (subjectName) {
            "Physics" -> apiService.getPhysicsSubjectDetails()
            "Chemistry" -> apiService.getChemistrySubjectDetails()
            else -> apiService.getMathematicsSubjectDetails()
        }

        serviceCall.enqueue(object : Callback<SubjectResponse> {
            override fun onResponse(
                call: Call<SubjectResponse>,
                response: Response<SubjectResponse>
            ) {
                if (response.isSuccessful) {
                    val subjectResponse = response.body()
                    val subject = subjectResponse?.record
                    chapterList.value = subject?.chapters ?: emptyList()
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}