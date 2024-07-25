package com.example.threadkotlinproject.mp3cutter.ui.mp3file.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.viewholder.Mp3ViewHolder
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.model.File

class Mp3Adapter : ListAdapter<File, Mp3ViewHolder>(Mp3DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Mp3ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mp3_item, parent, false)
        return Mp3ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Mp3ViewHolder, position: Int) {
        val file = getItem(position)
        holder.bindView(file)
    }
}