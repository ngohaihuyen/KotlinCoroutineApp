package com.example.threadkotlinproject.mp3cutter.ui.listfile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.ui.listfile.model.AudioFile
import com.example.threadkotlinproject.mp3cutter.ui.listfile.viewholder.ListFileViewHolder

class ListFileAdapter : ListAdapter<AudioFile, ListFileViewHolder>(ListFileDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mp3_item, parent, false)
        return ListFileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListFileViewHolder, position: Int) {
        val file = getItem(position)
        holder.bind(file)
    }
}