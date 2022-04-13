package com.example.myapplication_k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Timer().schedule(object :TimerTask(){
            override fun run() {
                val intent=Intent(this@SplashActivity,App::class.java)
                startActivity(intent)
                finish()
            }

        },2000L)
    }
}