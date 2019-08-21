package com.caesar.pandora

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.caesar.vote.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button1).setOnClickListener {
            doSing()
        }
        findViewById<Button>(R.id.button).setOnClickListener {
        }

    }


    fun doSing() {
//        runBlocking {
//
//        }
        GlobalScope.launch() {
//            launch {
//                kotlinx.coroutines.delay(7000)
//                Logout("才开始")
//            }
//            Logout("中午才开始")
//            kotlinx.coroutines.delay(3000)
            Logout(Thread.currentThread().name)
        }
        Logout("在江湖上脚后跟")
    }


    fun Logout(str: String) {
        Log.i("caesar", str)
    }


}
