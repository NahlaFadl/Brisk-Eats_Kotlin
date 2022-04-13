package com.example.myapplication_k

import API.ApiInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.email_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class EmailActivity : AppCompatActivity() {

    var myShared: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email_activity)

        continue_button.setOnClickListener {
            var str_emai=email_adress.text.toString()
           // getcode(str_emai)
        }

    }

//    fun getcode(enail:String) {
//
//        val retrfit= Retrofit.Builder()
//                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/password/forget/"+enail+"/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
//        val call=api.getCode()
//        call.enqueue(object : Callback<API.Response>
//        {
//            override fun onResponse(call: Call<API.Response>, response: Response<API.Response>) {
//                try {
//                    Toast.makeText(this@EmailActivity, response.message() + " succe", Toast.LENGTH_LONG).show()
//                }catch (ex: Exception){
//                    Toast.makeText(this@EmailActivity, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
//                }
//                val intent=Intent(this@EmailActivity,Email_VerificationActivity::class.java)
//                startActivity(intent)
//            }
//
//            override fun onFailure(call: Call<API.Response>, t: Throwable) {
//                Toast.makeText(this@EmailActivity,t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }
}