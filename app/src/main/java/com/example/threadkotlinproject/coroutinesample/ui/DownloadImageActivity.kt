package com.example.coroutineproject.coroutinesample.ui

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.coroutineproject.coroutinesample.callback.OnDownloadListener
import com.example.coroutineproject.coroutinesample.thread.ThreadManager
import com.example.threadkotlinproject.R
import java.io.File

class DownloadImageActivity : AppCompatActivity(), OnDownloadListener {

    private lateinit var requestBtn: AppCompatButton
    private lateinit var downloadBtn: AppCompatButton
    private lateinit var displayBtn: AppCompatButton
    private lateinit var resultImg: AppCompatImageView
    private val fileName = "Animal_lion.png"
    private val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Grosser_Panda.JPG/640px-Grosser_Panda.JPG"
    private lateinit var threadManager: ThreadManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_file_activity)

        initViews()
    }
    private fun initViews() {
        threadManager = ThreadManager()

        requestBtn = findViewById(R.id.request_btn)
        downloadBtn = findViewById(R.id.download_btn)
        resultImg = findViewById(R.id.result_img)
        displayBtn = findViewById(R.id.display_btn)

        requestBtn.setOnClickListener {
            requestPermissions()
        }
        downloadBtn.setOnClickListener {
            threadManager.downloadImage(applicationContext, imageUrl, this)
        }

        displayBtn.setOnClickListener {
            showImageFromPath()
        }
    }
    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", applicationContext.packageName))
                startActivityForResult(intent, 2296)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 2296)
            }
        }
    }
    private fun getImageFile(): File? {
        return try {
            val file = File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
            if (!file.exists()) {
                val isSuccess = file.createNewFile()
                if (isSuccess) {
                    file
                } else {
                    null
                }
            } else {
                file
            }
        } catch (e: Exception) {
            null
        }
    }
    private fun showImageFromPath() {
        val file = getImageFile()
        if (file != null && file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            resultImg.setImageBitmap(bitmap)
        } else {
            showAlertDialog(this, "Lỗi", "Hiển thị image file không thành công")
        }
    }
    private fun showAlertDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
    override fun onDownloadCompleted() {
        showImageFromPath()
    }
}