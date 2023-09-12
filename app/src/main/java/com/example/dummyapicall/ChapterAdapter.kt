//package com.example.dummyapicall
//import android.content.ContentValues.TAG
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.dummyapicall.databinding.ItemChapterBinding
//
//class ChapterAdapter(private val context: Context, private val chapters: List<Chapter>) :
//    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
//    private lateinit var binding: ItemChapterBinding
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding=DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()), R.layout.item_chapter, parent, false)
//        return ViewHolder(binding.root)
//
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val chapter = chapters[position]
//        holder.bind(chapter)
//    }
//
//    override fun getItemCount(): Int {
//        return chapters.size
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        init {
//            itemView.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val chapter = chapters[position]
//
//                    // Start the ChapterDetailActivity when clicking on a card
//                    val intent = Intent(context, VideosActivity::class.java)
//                    context.startActivity(intent)
//                }
//            }
//        }
//
//        fun bind(chapter: Chapter) {
//            Log.d(TAG,"image url ${chapter.chapter_image}" )
//
//            binding.chapterName.text = chapter.chapter_name
//            Glide.with(context)
//                .load(chapter.chapter_image)
//                .centerCrop()
//                .placeholder(R.drawable.baseline_error_24)
//                .error(R.drawable.baseline_approval_24)
//                .into(binding.chapterImage)
//            binding.chapterVideos.text = "Videos: ${chapter.number_of_videos}"
//            binding.chapterPractice.text = "Practices: ${chapter.number_of_practices}"
//        }
//    }
//}
//

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.Chapter
import com.example.dummyapicall.databinding.ItemChapterBinding
import com.example.dummyapicall.loadUrl

class ChapterAdapter(private val chapters: List<Chapter>,
                     private var onItemClicked: ((chapter: Chapter) -> Unit)
) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    inner class ViewHolder(private val binding: ItemChapterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(chapters[layoutPosition])
                }
            }
        }

        fun bind(chapter: Chapter) {
            Log.d(TAG, "SVG data: ${chapter.chapter_image}")

            // Load the SVG image using Glide
            binding.chapterImage.loadUrl(chapter.chapter_image)

            binding.chapterName.text = chapter.chapter_name
            binding.chapterVideos.text = "Videos: ${chapter.number_of_videos}"
            binding.chapterPractice.text = "Practices: ${chapter.number_of_practices}"
        }
    }
}
