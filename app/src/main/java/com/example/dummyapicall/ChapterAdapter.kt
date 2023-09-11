package com.example.dummyapicall
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dummyapicall.databinding.ItemChapterBinding

class ChapterAdapter(private val context: Context, private val chapters: List<Chapter>) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    private lateinit var binding: ItemChapterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()), R.layout.item_chapter, parent, false)
        return ViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val chapter = chapters[position]

                    // Start the ChapterDetailActivity when clicking on a card
                    val intent = Intent(context, VideosActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }

        fun bind(chapter: Chapter) {
            binding.chapterName.text = chapter.chapter_name
            Glide.with(binding.chapterImage)
                .load(chapter.chapter_image)
                .centerCrop()
                .into(binding.chapterImage)
            binding.chapterVideos.text = "Videos: ${chapter.number_of_videos}"
            binding.chapterPractice.text = "Practices: ${chapter.number_of_practices}"
        }
    }
}

