package com.example.threadkotlinproject

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.threadkotlinproject.coroutinesample.coroutine.DownloadManager
import com.example.threadkotlinproject.coroutinesample.coroutine.TestCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class HocSinh(var name: String, val age: Int)

class MainActivity : AppCompatActivity() {
    val test = TestCoroutine()/* private val value = HocSinh("", 10)
     private val value2 = HocSinh("", 10)


     private val value1 : HocSinh by lazy { HocSinh("", 10) }
     private val value2a : HocSinh by lazy { HocSinh("", 10) }
     */
    final val downloadManager = DownloadManager(this)

    private var textTv: TextView? = null

    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scope.launch {
            val path = downloadManager.downloadToExternalStorage(
                url = ""
            )
            withContext(Dispatchers.Main) {
                //handle path to some view
            }
        }

//       foo("hello friend", callback = {msg->
//           Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//       })
        textTv = findViewById(R.id.tv_test)
        scope.launch {
            test.downloadImage {
                Log.d("ManhNQ", "OnDone: ${Thread.currentThread().name}")
                textTv?.text = "Download done"
            }
        }
    }

}