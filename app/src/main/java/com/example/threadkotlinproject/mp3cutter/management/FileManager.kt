package com.example.threadkotlinproject.mp3cutter.management

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.threadkotlinproject.mp3cutter.ui.listfile.model.AudioFile

class FileManager(private val context: Context) {

    fun getAllAudioFiles(): List<AudioFile> {
        val audioFiles = mutableListOf<AudioFile>() //listOf bâất biến coòn mutable co the tdoi
        val contentResolver: ContentResolver = context.contentResolver // doi tg cho pheép truy cap caác dlieu cua hthong
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = "is_music != 0 AND title != ''" //xd dkien loọc de lay audiofile
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC" //dat thu tu sxep file tang dan

        val cursor: Cursor? = contentResolver.query( //t.hien de lay con tro dlieu tu contentresolver
            uri,
            null,
            selection,
            null,
            sortOrder
        )

        if (cursor != null && cursor.moveToFirst()) { //ktra xem con tro co null ko va bdau tu file dau tien
            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                val title: String = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                val audioFile = AudioFile(title, id.toInt())
                audioFiles.add(audioFile)
            } while (cursor.moveToNext())
            cursor.close()
        }

        return audioFiles
    }

    fun deleteAudioFile(audioId: Long): Boolean {
        val uri: Uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, audioId) //tajo uri de xoa file, method trong android de tao uri
        val contentResolver: ContentResolver = context.contentResolver
        val rowsDeleted = contentResolver.delete(uri, null, null)
        return rowsDeleted > 0
    }
}