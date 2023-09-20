package com.example.dummyapicall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.databinding.ItemSubjectBinding


class SubjectAdapter(
    private val subjects: List<Record>,
    private var onItemClicked: ((subject: Record) -> Unit)
) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        holder.bind(subject)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    inner class ViewHolder(private val binding: ItemSubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(subjects[layoutPosition])
                }
            }
        }

        fun bind(subject: Record) {
            // Load the SVG image using Glide
            binding.subjectImageView.loadUrl(subject.subjectImage)
            binding.subjectTextView.text = subject.subjectName

        }
    }
}