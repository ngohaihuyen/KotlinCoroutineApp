package com.example.threadkotlinproject.mp3cutter.ui.mp3file.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.model.File

class Mp3DiffUtil : DiffUtil.ItemCallback<File>() {
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem == newItem
    }
}