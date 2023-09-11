package com.example.dummyapicall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.R.id
import com.example.dummyapicall.R.layout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChapterAdapter

    lateinit var videoList: ArrayList<VideoItem>
//    val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
//    val viewPager = findViewById<ViewPager>(id.view_pager)
    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        recyclerView = findViewById(id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Retrofit for subject data
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//
//        // Create an instance of the SubjectApiService interface
        val apiService = retrofit.create(SubjectApiService::class.java)

        // Fetch subjects from the API
        val call = apiService.getSubject()
        call.enqueue(object : Callback<SubjectResponse> {
            override fun onResponse(
                call: Call<SubjectResponse>,
                response: Response<SubjectResponse>
            ) {
                if (response.isSuccessful) {
                    val subjectResponse = response.body()
                    val subject = subjectResponse?.record
                    val chapters = subject?.chapters ?: emptyList()
                    println("Chapters ${chapters.toString()}")

                    // Pass the subjects and chapters to the adapter
                    adapter = ChapterAdapter(this@MainActivity,chapters)
                    recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t:Throwable) {
                t.printStackTrace()
            }
        })
    }
}
