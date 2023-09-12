package com.example.dummyapicall

import ChapterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapicall.databinding.FragmentChapterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChapterFragment : Fragment() {
    private lateinit var binding:FragmentChapterBinding
    lateinit var adapter: ChapterAdapter
    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_chapter,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize Retrofit for subject data
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//
//        // Create an instance of the SubjectApiService interface
        val apiService = retrofit.create(ApiService::class.java)

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
                    println("Chapters $chapters")

                    // Pass the subjects and chapters to the adapter
                    adapter = ChapterAdapter(chapters){
                        findNavController().navigate(ChapterFragmentDirections.actionChapterFragmentToVideoFragment())
                    }
                    binding.recyclerView.adapter = adapter
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