package com.example.dummyapicall

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .placeholder(R.drawable.baseline_approval_24)
            .error(R.drawable.baseline_error_24)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }