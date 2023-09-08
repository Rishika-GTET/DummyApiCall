import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.dummyapicall.Chapter
import com.example.dummyapicall.ProductDetailActivity
import com.example.dummyapicall.R
import com.example.dummyapicall.Subject

class ChapterAdapter(
    private val context: Context,
    subject: Subject?,
    private val chapters: List<Chapter>
) : RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

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
        private val chapterImageImageView: ImageView = itemView.findViewById(R.id.chapter_image)
        private val chapterNameTextView: TextView = itemView.findViewById(R.id.chapter_name)
        private val numberOfVideosTextView: TextView = itemView.findViewById(R.id.chapter_videos)
        private val numberOfPracticesTextView: TextView = itemView.findViewById(R.id.chapter_practice)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val chapter = chapters[position]

                    // Start the ChapterDetailActivity when clicking on a card
                    val intent = Intent(context, ProductDetailActivity::class.java)
                    intent.putExtra("chapter", chapter) // Pass chapter data to the detail activity
                    context.startActivity(intent)
                }
            }
        }


        fun bind(chapter: Chapter) {
            // Load chapter image using Glide with SVG support
            Glide.with(itemView)
//                .`as`(com.bumptech.glide.load.resource.bitmap.Bitmap::class.java)
                .load(chapter.chapter_image)
                .apply(RequestOptions.overrideOf(200, 200)) // Set desired image size
                .apply(RequestOptions.centerCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(chapterImageImageView)

            chapterNameTextView.text = chapter.chapter_name
            numberOfVideosTextView.text = "Videos: ${chapter.number_of_videos}"
            numberOfPracticesTextView.text = "Practices: ${chapter.number_of_practices}"
        }
    }
}
