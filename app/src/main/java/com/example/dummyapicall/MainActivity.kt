package com.example.dummyapicall

import ProductAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val baseUrl = "https://dummyjson.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)


        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ProductApiService interface
        val apiService = retrofit.create(ProductApiService::class.java)

        // Fetch products from the API
        val call = apiService.getProducts()
        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    val products = productResponse?.products ?: emptyList()
                    println("Response is ${products.toString()}")
                    adapter = ProductAdapter(this@MainActivity,products)
                    recyclerView.adapter = adapter
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}