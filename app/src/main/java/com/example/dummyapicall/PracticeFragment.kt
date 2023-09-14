package com.example.dummyapicall
import ChapterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dummyapicall.Retrofit.retrofitService
import com.example.dummyapicall.databinding.FragmentChapterBinding
import com.example.dummyapicall.databinding.LayoutBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PracticeFragmentFragment : Fragment() {
    private lateinit var binding: FragmentChapterBinding
    private lateinit var dialogBinding: LayoutBottomSheetDialogBinding
    lateinit var adapter: ChapterAdapter
    private var subjects: List<Record>? = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chapter, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chapterSelectionButton.setOnClickListener {
            openBottomSheetDialog()
        }


        getSubjectList()
        // Fetch subjects from the API
        getMathematicsChaptersList()
    }

    private fun getSubjectList() {
        val call = retrofitService.getSubjectList()
        call.enqueue(object : Callback<SubjectListResponse?> {
            override fun onResponse(
                call: Call<SubjectListResponse?>,
                response: Response<SubjectListResponse?>
            ) {
                if (response.isSuccessful) {
                    subjects = response.body()?.record
                    binding.chapterTextView.text = subjects?.getOrNull(0)?.subjectName
                    binding.chapterImageView.loadUrl(subjects?.getOrNull(0)?.subjectImage!!)
                }
            }

            override fun onFailure(call: Call<SubjectListResponse?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getMathematicsChaptersList(){
        val call = retrofitService.getMathematicsSubjectDetails()
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
                    adapter = ChapterAdapter(chapters) {
                        findNavController().navigate(ChapterFragmentDirections.actionChapterFragmentToChapterDetailsFragment())
                    }
                    binding.recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getPhysicsChaptersList(){
        val call = retrofitService.getPhysicsSubjectDetails()
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
                    adapter = ChapterAdapter(chapters) {
                        findNavController().navigate(ChapterFragmentDirections.actionChapterFragmentToChapterDetailsFragment())
                    }
                    binding.recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getChemistryChaptersList(){
        val call = retrofitService.getChemistrySubjectDetails()
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
                    adapter = ChapterAdapter(chapters) {
                        findNavController().navigate(ChapterFragmentDirections.actionChapterFragmentToChapterDetailsFragment())
                    }
                    binding.recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun openBottomSheetDialog() {
        dialogBinding = LayoutBottomSheetDialogBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(dialogBinding.root)
        dialogBinding.recyclerViewSubject.adapter =
            SubjectAdapter(subjects = subjects!!) { selectedSubject ->
                binding.chapterTextView.text = selectedSubject.subjectName
                binding.chapterImageView.loadUrl(selectedSubject.subjectImage)
                if(selectedSubject.subjectName == "Physics"){
                    getPhysicsChaptersList()
                }else if(selectedSubject.subjectName == "Chemistry"){
                    getChemistryChaptersList()
                }else{
                    getMathematicsChaptersList()
                }
                dialog.cancel()
            }
        dialog.show()
    }


}