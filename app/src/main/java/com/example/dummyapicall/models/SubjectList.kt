package com.example.dummyapicall

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Chapter(
    @SerializedName("chapter_name")
    val chapterName: String,
    @SerializedName("number_of_videos")
    val numberOfVideos: String,
    @SerializedName("number_of_practices")
    val numberOfPractices: String,
    @SerializedName("chapter_image")
    val chapterImage: String
)

data class SubjectResponse(
    val record: Subject,
) : Serializable

data class Subject(
    @SerializedName("subject_id")
    val subjectId: String,
    val subjectName: String,
    val subjectImage: String,
    val chapters: List<Chapter>
) : Serializable