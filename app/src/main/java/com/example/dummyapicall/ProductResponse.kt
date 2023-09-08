package com.example.dummyapicall

import java.io.Serializable

//data class ProductResponse(
//    val products: List<Product>
//)


data class SubjectResponse(
    val record: Subject,
) : Serializable