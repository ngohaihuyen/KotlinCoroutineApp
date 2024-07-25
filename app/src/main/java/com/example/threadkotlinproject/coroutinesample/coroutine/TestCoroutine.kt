package com.example.threadkotlinproject.coroutinesample.coroutine

import android.provider.Settings
import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TestCoroutine {
    suspend fun downloadImage(onUpdateUI: suspend () -> Unit) =
        withContext(Dispatchers.Unconfined) {
            Log.d("ManhNQ", "downloadImage [1]: ${Thread.currentThread().name}")
            delay(1000L)
            Log.d("ManhNQ", "downloadImage [2]: ${Thread.currentThread().name}")
            delay(1000)
            withContext(Dispatchers.Main) {
                onUpdateUI()
            }
        }

    //viết lại slide
    // normal job & supervisorJob
    //flow in kotlin coroutine
    //async await ?? what is this? when use it?
}


fun main() {
//    runningCoroutine()
    runBlocking {
        launch {       // coroutine 1
            delay(200L)
            println("Task from runBlocking")   // line code 1
        }

      /*  coroutineScope {
            launch {
                delay(500L)
                println("Task from nested launch") // line code 2
            }

            delay(100L)
            println("Task from coroutine scope") // line code 3
        }
*/
        println("Task from nested launch") // li

        println("Coroutine scope is over") // line code 4
    }
}

fun runningCoroutine() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val num1 = async(start = CoroutineStart.LAZY, block = { getNumber1() })
        val num2 = async(start = CoroutineStart.LAZY) { getNumber2() }
        num1.start()
        num2.start()

        println("total time result = ${num1.await() + num2.await()} --- time: ${System.currentTimeMillis() - startTime}")

    }
}

suspend fun getNumber1(): Int {
    delay(1000)
    return 2
}

suspend fun getNumber2(): Int {
    delay(1000)
    return 5
}

suspend fun download() {
    delay(500)
    println("Download image")
}

fun runningCoroutine2() {
    println("-----------**-----------")
    runBlocking {
        val job = launch {
            var count = 0
            while (count < 5) {
                if (isActive) {
                    println("[2] running on $count ${isActive}")
                    delay(500)
                    count++
                }
            }
        }
        job.cancel()

        println("main: Downloading function")
    }

}

