package com.example.dummyapicall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SubjectDetailAdapter(private val context: Context, private val subjects: List<Subject>) :
    RecyclerView.Adapter<SubjectDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        holder.bind(subject)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subjectNameTextView: TextView = itemView.findViewById(R.id.chapter_name)
        private val subjectImageView: ImageView = itemView.findViewById(R.id.chapter_name)

        fun bind(subject: Subject) {
            subjectNameTextView.text = subject.subjectName
            Glide.with(itemView)
                .load(subject.subjectImage)
                .centerCrop()
                .into(subjectImageView)
        }
    }
}
