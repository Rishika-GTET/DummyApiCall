package com.example.dummyapicall.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.Chapter
import com.example.dummyapicall.databinding.ItemChapterBinding
import com.example.dummyapicall.loadUrl

class ChapterAdapter(
    chapters: List<Chapter>,
    private var onItemClicked: ((chapter: Chapter) -> Unit)
) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    var allChapters = chapters


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = allChapters[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return allChapters.size
    }

    inner class ViewHolder(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(allChapters[layoutPosition])
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(chapter: Chapter) {
            Log.d(TAG, "SVG data: ${chapter.chapterImage}")
            // Load the SVG image using Glide
            binding.chapterImage.loadUrl(chapter.chapterImage)
            binding.chapterName.text = chapter.chapterName
            binding.chapterVideos.text = "Videos: ${chapter.numberOfVideos}"
            binding.chapterPractice.text = "Practices: ${chapter.numberOfPractices}"
        }
    }
}
