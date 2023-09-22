package com.example.dummyapicall.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dummyapicall.R
import com.example.dummyapicall.adapter.ChapterAdapter
import com.example.dummyapicall.adapter.SubjectAdapter
import com.example.dummyapicall.databinding.FragmentChapterBinding
import com.example.dummyapicall.databinding.LayoutBottomSheetDialogBinding
import com.example.dummyapicall.loadUrl
import com.example.dummyapicall.viewmodels.ChapterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ChapterFragment : Fragment() {
    private lateinit var binding: FragmentChapterBinding
    private lateinit var dialogBinding: LayoutBottomSheetDialogBinding
    private lateinit var viewModel: ChapterViewModel
    lateinit var adapter: ChapterAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chapter, container, false)
        viewModel = ViewModelProvider(this)[ChapterViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chapterSelectionButton.setOnClickListener {
            openBottomSheetDialog()
        }
        lifecycleScope.launch {
            viewModel.chapterList.collectLatest {
                adapter = ChapterAdapter(it) {
                    findNavController().navigate(ChapterFragmentDirections.actionChapterFragmentToChapterDetailsFragment(it.chapterName))
                }
                binding.recyclerView.adapter=adapter

            }
        }


        lifecycleScope.launch {
            viewModel.subjectImage.collectLatest {
                binding.chapterImageView.loadUrl(it)
            }
        }
        lifecycleScope.launch {
            viewModel.subjectName.collectLatest {
                binding.chapterTextView.text = it
            }
        }
    }

    private fun openBottomSheetDialog() {
        dialogBinding = LayoutBottomSheetDialogBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(dialogBinding.root)
        dialogBinding.recyclerViewSubject.adapter =
            SubjectAdapter(subjects = viewModel.subjectList.value) { selectedSubject ->
                viewModel.subjectName.value = selectedSubject.subjectName
                viewModel.subjectImage.value = selectedSubject.subjectImage
                viewModel.getSubjectChapters(selectedSubject.subjectName)
                dialog.cancel()
            }
        dialog.show()
    }


}