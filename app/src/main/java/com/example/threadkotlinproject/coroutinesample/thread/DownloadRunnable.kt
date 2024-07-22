package com.example.coroutineproject.coroutinesample.thread

import android.content.Context
import android.os.Environment
import com.example.coroutineproject.coroutinesample.callback.OnDownloadListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadRunnable(
    private val context: Context,
    private val url: String,
    private val listener: OnDownloadListener,
    private val handler: android.os.Handler ) : Runnable {

    private val fileName = "Animal_panda.png"
    override fun run() {
        downloadToExternalStorage(url)
    }
    private fun downloadToExternalStorage(url: String) {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null //vì inputstr có thể null => sd "?" để có thể sd dc luôn
        var outputStream: FileOutputStream? = null

        try {
            val imageUrl = URL(url)
            connection = imageUrl.openConnection() as HttpURLConnection
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                val file = getImageFile()

                val totalContent = connection.contentLength
                var currentPercent = 0

                outputStream = FileOutputStream(file)

                val buffer = ByteArray(1024)
                var bytesRead = inputStream.read(buffer)
                while (bytesRead != -1) {
                    currentPercent += bytesRead

                    if (outputStream != null) {
                        outputStream.write(buffer, 0, bytesRead)
                    }
                    bytesRead = inputStream.read(buffer)
                }

                if (outputStream != null) {
                    outputStream.close()
                }
                inputStream.close()
                connection.disconnect()
                handler.post { listener.onDownloadCompleted() }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            outputStream?.close()
            connection?.disconnect()
        }
    }

    private fun getImageFile(): File? {
        return try {
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
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

}