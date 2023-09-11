package com.example.dummyapicall

import java.io.Serializable

data class VideoResponse(
    val record: List<VideoItem>,
) : Serializable