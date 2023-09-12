package com.example.dummyapicall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapicall.databinding.VideoItemBinding

class VideoAdapter(private val videoList: List<VideoItem>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private lateinit var binding: VideoItemBinding

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        binding.videoImage.loadUrl("https://upload.wikimedia.org/wikipedia/commons/0/02/SVG_logo.svg")
        binding.titleText.text=video.title
        binding.descriptionText.text=video.subTitle
    }
}
