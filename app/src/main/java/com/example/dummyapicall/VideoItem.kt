package com.example.dummyapicall

import java.io.Serializable

data class VideoItem(
    val id : String,
    val image:String,
    val title:String,
    val subTitle:String,
    val videoTotalDuration:String
) : Serializable
