package com.example.coroutineproject.coroutinesample.thread

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.coroutineproject.coroutinesample.callback.OnDownloadListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThreadManager {
    private val executor: ExecutorService = Executors.newCachedThreadPool()
    private val handler: Handler = Handler(Looper.getMainLooper())
    fun downloadImage(context: Context, url: String, listener: OnDownloadListener) {
        executor.execute(DownloadRunnable(context, url, listener, handler))
    }
}