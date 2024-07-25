package com.example.threadkotlinproject.mp3cutter.ui.listfile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.threadkotlinproject.R
import com.example.threadkotlinproject.mp3cutter.management.FileManager
import com.example.threadkotlinproject.mp3cutter.ui.listfile.adapter.ListFileAdapter


class FileListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListFileAdapter
    private lateinit var fileManager: FileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio_files_list_activity)

        initViews()
        initData()

    }
    private fun initViews() {
        recyclerView = findViewById(R.id.audio_files_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        fileManager = FileManager(this)
        val audioFiles = fileManager.getAllAudioFiles()

        adapter = ListFileAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(audioFiles)    }


}