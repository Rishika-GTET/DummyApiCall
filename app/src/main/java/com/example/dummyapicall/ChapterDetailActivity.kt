package com.example.dummyapicall
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ChapterAdapter(private val context: Context, private val chapters: List<Chapter>) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chapterNameTextView: TextView = itemView.findViewById(R.id.chapter_name)
        private val numberOfVideosTextView: TextView = itemView.findViewById(R.id.chapter_videos)
        private val numberOfPracticesTextView: TextView = itemView.findViewById(R.id.chapter_practice)
        private val chapterImageImageView: ImageView = itemView.findViewById(R.id.chapter_image)

        fun bind(chapter: Chapter) {
            chapterNameTextView.text = chapter.chapter_name
            Glide.with(itemView)
                .load(chapter.chapter_image)
                .centerCrop()
                .into(chapterImageImageView)
            numberOfVideosTextView.text = "Videos: ${chapter.number_of_videos}"
            numberOfPracticesTextView.text = "Practices: ${chapter.number_of_practices}"
        }
    }
}

