package com.example.myapplication_k

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_password.*

class Password : AppCompatActivity() {

    var myShared: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        save_button.setOnClickListener {
          //  gePassword()
        }
    }

//    fun gePassword()
//    {
//        val retrfit= Retrofit.Builder()
//                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
//        val call=api.n
//        call.enqueue(object : Callback<RegisterData>
//        {
//            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
//                try {
//                    Toast.makeText(this@Password, response.body()!!.Data.token + " succe", Toast.LENGTH_LONG).show()
//                }catch (ex: Exception){
//                    Toast.makeText(this@Password, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
//                Toast.makeText(this@Password,t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }
}