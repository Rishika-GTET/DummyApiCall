package com.example.dummyapicall.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.models.PracticeRecord
import com.example.dummyapicall.databinding.PracticeRecyclerViewBinding

class PracticeRecyclerViewAdapter(
    val levelList: List<PracticeRecord>
) :
    RecyclerView.Adapter<PracticeRecyclerViewAdapter.PracticeRecyclerViewHolder>() {
    private lateinit var binding: PracticeRecyclerViewBinding
    private lateinit var adapter: PracticeAdapter

    class PracticeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeRecyclerViewHolder {
        binding =
            PracticeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PracticeRecyclerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return levelList.size
    }

    override fun onBindViewHolder(holder: PracticeRecyclerViewHolder, position: Int) {
        val data = levelList[position]
        adapter= PracticeAdapter(data.practiceLevelModels)
        binding.label.text = data.levelName
        binding.labelDesc.setTextColor(Color.parseColor(data.levelColor))
        binding.levelRecyclerView.adapter = adapter
    }
}

