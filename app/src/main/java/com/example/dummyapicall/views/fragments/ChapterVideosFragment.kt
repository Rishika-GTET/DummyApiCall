package com.example.dummyapicall.views.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dummyapicall.R
import com.example.dummyapicall.adapter.VideoAdapter
import com.example.dummyapicall.databinding.FragmentChapterVideosBinding
import com.example.dummyapicall.viewmodels.ChapterVideosViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChapterVideosFragment : Fragment() {
    private lateinit var binding: FragmentChapterVideosBinding
    lateinit var adapter: VideoAdapter
    private lateinit var viewModel: ChapterVideosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_chapter_videos, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ChapterVideosViewModel::class.java]

        lifecycleScope.launch {
            viewModel.videos.collectLatest {
                adapter= VideoAdapter(it){
                    Toast.makeText(requireContext(),it.title,Toast.LENGTH_LONG).show()
                }
                binding.recyclerView.adapter=adapter
            }
        }

    }

}
