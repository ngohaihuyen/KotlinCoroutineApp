package com.example.threadkotlinproject.coroutinesample.coroutine

import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

class TestCoroutine {
    val myScope = CoroutineScope(Dispatchers.IO)

    suspend fun run() {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("ManhNQ", "run: $exception")
        }

        val job = myScope.launch(handler) {
            var count = 0
            while (count < 5) {
                if (count == 3) {
                    throw Exception("This suspend function occur error")
                }
                delay(500)
                count++
            }
        }
        delay(1600)
        job.cancel()

    }

    //viết lại slide
    // normal job & supervisorJob
    //flow in kotlin coroutine
    //async await ?? what is this? when use it?
}
