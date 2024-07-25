package com.example.threadkotlinproject.mp3cutter.ui.mp3file

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.adapter.Mp3Adapter
import com.example.threadkotlinproject.mp3cutter.ui.mp3file.model.File

class FileMp3Activity: AppCompatActivity()  {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Mp3Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mp3_activity)
        recyclerView = findViewById(R.id.mp3_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val audioFiles = listOf(
            File("Audio Cutter", R.drawable.audiocutter),
            File("Audio Merger", R.drawable.auidomerge),
            File("Audio Mixer", R.drawable.mixer),
            File("Contacts", R.drawable.contact),
            File("My Studio", R.drawable.mystudio),
            File("Flash Call", R.drawable.flashcall),
            File("Audio Effect", R.drawable.efffect),
            File("Voice Changer", R.drawable.voicechanger),
            File("Recording", R.drawable.recording),
            File("Convert Audio", R.drawable.convert),
            File("Noise Reducer", R.drawable.noisereducer),
            File("Reverse", R.drawable.reverse),
            File("Convert Video", R.drawable.conversevid)
        )
        adapter = Mp3Adapter()
        recyclerView.adapter = adapter
        adapter.submitList(audioFiles)
    }
}