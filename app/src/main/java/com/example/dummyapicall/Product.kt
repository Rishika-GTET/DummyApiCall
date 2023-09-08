package com.example.dummyapicall

import java.io.Serializable

//data class Product(
//    val id: Int,
//    val title: String,
//    val description: String,
//    val price: Double,
//    val thumbnail: String
//) : Serializable

data class Chapter(
    val chapter_id: String,
    val chapter_name: String,
    val number_of_videos: String,
    val number_of_practices: String,
    val chapter_image: String
) : Serializable

data class Subject(
    val subject_id: String,
    val subjectName: String,
    val subjectImage: String,
    val chapters: List<Chapter>
) : Serializable