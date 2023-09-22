package com.example.dummyapicall.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dummyapicall.models.PracticeListItem
import com.example.dummyapicall.models.PracticeRecord
import com.example.dummyapicall.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ChapterPracticeViewModel:ViewModel() {

    @Inject
    lateinit var apiService: ApiService

    var practiceList: MutableStateFlow<List<PracticeRecord>> = MutableStateFlow(emptyList())
    init {
        getChapterPracticeList()
    }
    private fun getChapterPracticeList(){
        val call = apiService.getPracticeList()
        call.enqueue(object : Callback<PracticeListItem> {
            override fun onResponse(
                call: Call<PracticeListItem>,
                response: Response<PracticeListItem>
            ) {
                if (response.isSuccessful) {
                    val response = response.body()
                    practiceList.value = response?.record ?: emptyList()

                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<PracticeListItem>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }
}