package com.example.threadkotlinproject.coroutinesample.coroutine

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

class DownloadManager(val context: Context) {

    private fun downloadToExternalStorage(
        url: String,
        onSuccess: (String) -> Unit,
        onError: (IOException) -> Unit
    ) {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? =
            null //vì inputstr có thể null => sd "?" để có thể sd dc luôn
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
                onSuccess(if (file != null) file.absolutePath else "")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            onError(e)
        } finally {
            inputStream?.close()
            outputStream?.close()
            connection?.disconnect()
        }
    }

    private fun getImageFile(): File? {
        return try {
            val file =
                File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "fileName")
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