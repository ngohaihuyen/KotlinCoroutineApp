package com.example.threadkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.threadkotlinproject.coroutinesample.coroutine.TestCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


data class HocSinh(var name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    /* private val value = HocSinh("", 10)
     private val value2 = HocSinh("", 10)


     private val value1 : HocSinh by lazy { HocSinh("", 10) }
     private val value2a : HocSinh by lazy { HocSinh("", 10) }
     */

    val scope = CoroutineScope(Dispatchers.Main+ SupervisorJob())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       foo("hello friend", callback = {msg->
//           Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//       })

        scope.launch {
            val test = TestCoroutine()
            test.run()

        }
    }

}