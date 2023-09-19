package com.example.dummyapicall

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dummyapicall.databinding.FragmentChapterPracticeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChapterPracticeFragment : Fragment() {
    private lateinit var binding: FragmentChapterPracticeBinding
    lateinit var level1Adapter: PracticeAdapter
    lateinit var level2Adapter: PracticeAdapter
    lateinit var level3Adapter: PracticeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chapter_practice, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch subjects from the API
        val call = Retrofit.retrofitService.getPracticeList()
        call.enqueue(object : Callback<PracticeListItem> {
            override fun onResponse(
                call: Call<PracticeListItem>,
                response: Response<PracticeListItem>
            ) {
                if (response.isSuccessful) {
                    val videoResponse = response.body()
                    val data = videoResponse?.record
                    if (!data.isNullOrEmpty()) {
                        level1Adapter = PracticeAdapter(data[0].practiceLevelModels) {
                            // start the text
                        }
                        level2Adapter = PracticeAdapter(data[1].practiceLevelModels) {
                            // start the text
                        }
                        level3Adapter = PracticeAdapter(data[2].practiceLevelModels) {
                            // start the text
                        }
                        setData(data)
                    }
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<PracticeListItem>, throwable: Throwable) {
                throwable.printStackTrace()
            }
        })
    }

    private fun setData(data: List<PracticeRecord>) {
        binding.level1LabelTextView.text = data[0].levelName
        binding.level1Label.setTextColor(Color.parseColor(data[0].levelColor))
        binding.level2LabelTextView.text = data[1].levelName
        binding.level2Label.setTextColor(Color.parseColor(data[0].levelColor))
        binding.level3LabelTextView.text = data[2].levelName
        binding.level3Label.setTextColor(Color.parseColor(data[0].levelColor))
        binding.level1RecyclerView.adapter = level1Adapter
        binding.level2RecyclerView.adapter = level2Adapter
        binding.level3RecyclerView.adapter = level3Adapter
    }

}