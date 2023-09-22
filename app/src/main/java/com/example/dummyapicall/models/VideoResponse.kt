package com.example.dummyapicall.models

import java.io.Serializable

data class VideoResponse(
    val record: List<VideoItem>,
) : Serializable