package com.example.coroutineproject.clockview
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.os.Handler

import android.os.Looper

import android.util.Log
import java.util.Calendar
import com.example.threadkotlinproject.R


class ClockViewActivity : AppCompatActivity (){

    private lateinit var clockView: ClockViewCustom
    private lateinit var handler: Handler
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clock_view_act)

        handler = Handler(Looper.myLooper()!!)
        calendar = Calendar.getInstance()
        initViews()
        scheduleTimer()
    }

    private fun scheduleTimer() {
        handler.postDelayed(::setTimer, 1000)
    }

    private fun setTimer() {
        Log.d("ManhNQ", "setTimer: ")
        calendar.timeInMillis = System.currentTimeMillis()

        val hours = calendar.get(Calendar.HOUR)
        val minutes = calendar.get(Calendar.MINUTE)
        val seconds = calendar.get(Calendar.SECOND)
        clockView.setTime(hours, minutes, seconds)
        scheduleTimer()
    }

    private fun initViews() {
        clockView = findViewById(R.id.clock_view)
        clockView.setIsNumber(false)

    }
}
