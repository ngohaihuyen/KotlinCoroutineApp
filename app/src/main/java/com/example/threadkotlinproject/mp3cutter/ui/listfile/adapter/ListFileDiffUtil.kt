package com.example.threadkotlinproject.mp3cutter.ui.listfile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.threadkotlinproject.mp3cutter.ui.listfile.model.AudioFile

class ListFileDiffUtil: DiffUtil.ItemCallback<AudioFile>() {
    override fun areItemsTheSame(oldItem: AudioFile, newItem: AudioFile): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AudioFile, newItem: AudioFile): Boolean {
        return oldItem == newItem
    }
}