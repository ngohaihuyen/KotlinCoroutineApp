package com.example.threadkotlinproject.mp3cutter.ui.mp3file.viewholder

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.ui.listfile.FileListActivity
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.model.File

class Mp3ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: AppCompatTextView = itemView.findViewById(R.id.title_txt)
    private val iconImageView: AppCompatImageView = itemView.findViewById(R.id.icon_img)

    fun bindView(file: File) {
        titleTextView.text = file.title
        iconImageView.setImageResource(file.id)

        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, FileListActivity::class.java)
            context.startActivity(intent)
        }
    }
}