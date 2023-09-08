package com.example.dummyapicall

//import ProductAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout



class ProductDetailActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
//        textView = findViewById(R.id.product_details)

        // Retrieve the list of products from the Intent extras
        val productList = intent.getSerializableExtra("productList") as? ArrayList<Subject>
        println("Products $productList")

    }

}
