package com.example.threadkotlinproject.mp3cutter.ui.listfile.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.ui.listfile.model.AudioFile

class ListFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titletxt: AppCompatTextView = itemView.findViewById(R.id.title_txt)
    private val fileiconimg: AppCompatImageView = itemView.findViewById(R.id.icon_img)

    fun bind(audioFile: AudioFile) {
        titletxt.text = audioFile.title
        fileiconimg.setImageResource(audioFile.id)
    }
}