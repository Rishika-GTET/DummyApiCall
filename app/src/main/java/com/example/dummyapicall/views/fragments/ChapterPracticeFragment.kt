package com.example.dummyapicall.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dummyapicall.R
import com.example.dummyapicall.adapter.PracticeRecyclerViewAdapter
import com.example.dummyapicall.databinding.FragmentChapterPracticeBinding
import com.example.dummyapicall.viewmodels.ChapterPracticeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ChapterPracticeFragment : Fragment() {
    private lateinit var binding: FragmentChapterPracticeBinding
    private lateinit var viewModel: ChapterPracticeViewModel
    private lateinit var adapter: PracticeRecyclerViewAdapter

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
        viewModel = ViewModelProvider(this)[ChapterPracticeViewModel::class.java]

        lifecycleScope.launch {
            viewModel.practiceList.collectLatest { data ->
                adapter=PracticeRecyclerViewAdapter(data)
                binding.recyclerView.adapter=adapter
            }
        }
    }
}