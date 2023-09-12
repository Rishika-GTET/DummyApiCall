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
