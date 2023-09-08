package com.example.dummyapicall
//
//import ProductAdapter
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: ProductAdapter
//
//    private val baseUrl = "https://dummyjson.com/"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        recyclerView = findViewById(R.id.recycler_view)
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//        // Initialize Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Create an instance of the ProductApiService interface
//        val apiService = retrofit.create(ProductApiService::class.java)
//
//        // Fetch products from the API
//        val call = apiService.getProducts()
//        call.enqueue(object : Callback<ProductResponse> {
//            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
//                if (response.isSuccessful) {
//                    val productResponse = response.body()
//                    val products = productResponse?.products ?: emptyList()
//                    println("Response is ${products.toString()}")
//                    adapter = ProductAdapter(this@MainActivity,products)
//                    recyclerView.adapter = adapter
//                } else {
//                    // Handle the error
//                }
//            }
//
//            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//}


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.R
import com.example.dummyapicall.SubjectApiService
import com.example.dummyapicall.SubjectResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChapterAdapter

    private val baseUrl = "https://api.jsonbin.io/v3/b/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Retrofit for subject data
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the SubjectApiService interface
        val apiService = retrofit.create(SubjectApiService::class.java)

        // Fetch subjects from the API
        val call = apiService.getSubject()
        call.enqueue(object : Callback<SubjectResponse> {
            override fun onResponse(
                call: Call<SubjectResponse>,
                response: Response<SubjectResponse>
            ) {
                if (response.isSuccessful) {
                    val subjectResponse = response.body()
                    val subject = subjectResponse?.record
                    val chapters = subject?.chapters ?: emptyList()
                    println("Chapters ${chapters.toString()}")

                    // Pass the subjects and chapters to the adapter
                    adapter = ChapterAdapter(this@MainActivity,chapters)
                    recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
