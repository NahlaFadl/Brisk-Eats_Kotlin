package com.example.myapplication_k

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class App:AppCompatActivity() {

    var myShared: SharedPreferences?=null

    override fun onStart() {
        super.onStart()
        myShared=getSharedPreferences("myShared",0)
        var token =myShared?.getString("token","")
        if (token != ""){
            val intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
        else if (token == "")
        {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}