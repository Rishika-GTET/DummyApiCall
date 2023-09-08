//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.dummyapicall.Product
//import com.example.dummyapicall.ProductDetailActivity
//import com.example.dummyapicall.R
//
//class ProductAdapter(private val context: Context,val products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
//
//    // Define an interface for item click handling
//    interface OnProductItemClickListener {
//        fun onProductItemClick(product: Product)
//    }
//
//    private var itemClickListener: OnProductItemClickListener? = null
//
//    // Setter method for the item click listener
//    fun setOnProductItemClickListener(listener: OnProductItemClickListener) {
//        itemClickListener = listener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val product = products[position]
//        holder.bind(product)
//    }
//
//    override fun getItemCount(): Int {
//        return products.size
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.product_thumbnail)
//        private val titleTextView: TextView = itemView.findViewById(R.id.product_title)
//
//        init {
//            itemView.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val product = products[position]
//
//                    // Start the ProductDetailActivity when clicking on a card
//                    val intent = Intent(context, ProductDetailActivity::class.java)
////                    intent.putExtra("productList", ArrayList(products))
//                    context.startActivity(intent)
//                }
//            }
//        }
//
//        fun bind(product: Product) {
//            titleTextView.text = product.title
//            Glide.with(itemView)
//                .load(product.thumbnail)
//                .centerCrop()
//                .into(thumbnailImageView)
//        }
//    }
//}
//
//
//
//
//
//
