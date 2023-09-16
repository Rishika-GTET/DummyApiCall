package com.example.dummyapicall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.databinding.PracticeCardItemBinding

class PracticeAdapter(private val practiceList: List<PracticeLevelModel>,
    private var onItemClick:(PracticeLevelModel)->(Unit)={}) :
    RecyclerView.Adapter<PracticeAdapter.PracticeViewHolder>() {
    private lateinit var binding: PracticeCardItemBinding

    class PracticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeViewHolder {
        binding = PracticeCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PracticeViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return practiceList.size
    }

    override fun onBindViewHolder(holder: PracticeViewHolder, position: Int) {
        val practiceItem = practiceList[position]
        binding.progressBar.progress = practiceItem.progressPercent
        binding.progressTextView.text = practiceItem.progressPercent.toString()
        binding.titleTextView.text = practiceItem.title
        binding.subtitleTextView.text = "${practiceItem.completedQuestions}/${practiceItem.totalQuestions}"
        binding.startButton.setOnClickListener {
            onItemClick(practiceItem)
        }
    }
}
