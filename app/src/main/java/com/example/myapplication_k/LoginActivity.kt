package com.example.myapplication_k

import API.ApiInterface
import API.ResponseRegister
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_1.*
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    var myShared: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        //login
        login_button.setOnClickListener {
            var numberStr=login_phon.text.toString()
            var password=login_password.text.toString()
            logIn(numberStr,password, "user", "aaaaaaaaaaa","ios")

        }

        // go to sinUp activity
        sinUp_log.setOnClickListener {
            val intent=Intent(this,Register_1::class.java)
            startActivity(intent)
        }

        //forget password
        loginForget_pass.setOnClickListener {
            val intent=Intent(this,EmailActivity::class.java)
            startActivity(intent)
        }

        // to skip
        lpgin_skip.setOnClickListener {
            val intent= Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    fun logIn(number:String,password:String, user_type:String ,push_token:String,device_type:String) {

        val retrfit= Retrofit.Builder()
                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
        val call=api.login(number, password, user_type, push_token, device_type)
        call.enqueue(object : Callback<ResponseRegister>
        {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                try {
                    //data
                    Toast.makeText(this@LoginActivity,  "successful", Toast.LENGTH_LONG).show()
                }catch (ex: Exception){
                    Toast.makeText(this@LoginActivity, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
                }
                val intent=Intent(this@LoginActivity,ProfileActivity::class.java)
                startActivity(intent)
                //Shared Preferences for token
                myShared=getSharedPreferences("myShared",0)
                var editor:SharedPreferences.Editor=myShared!!.edit()
                //data
                editor.putString("token",response.body()!!.Data.token)
//                editor.putString("name",response.body()!!.Data.user.first_name)
//                editor.putString("profile",response.body()!!.Data.user.profile_path)
                editor.commit()
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Toast.makeText(this@LoginActivity,t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}