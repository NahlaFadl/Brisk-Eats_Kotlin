package com.example.myapplication_k

import API.ApiInterface
import API.ResponseRegister
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Register_1 : AppCompatActivity() {

    var myShared: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_1)

        // register
        register_but.setOnClickListener {

            var nameSt = name_reg.text.toString()
            var emailSt = email_reg.text.toString()
            var passwordSt = password_reg.text.toString()
            var phoneSt = phone_reg.text.toString()

            sinUp(nameSt, "cairo", 555, phoneSt, emailSt, passwordSt, "user")


        }

        // to skip
        reg_skip.setOnClickListener {
            val intent= Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
    }

     fun sinUp(name:String,country:String,phone_code:Int, mobile_number:String,email:String,password:String , user_type:String) {

         val retrfit= Retrofit.Builder()
             .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         val api: ApiInterface =retrfit.create(ApiInterface::class.java)
         val call=api.register(name,country,phone_code,mobile_number,email,password,user_type)
         call.enqueue(object : Callback<ResponseRegister>
         {
             override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                 try {
                     //data
                     Toast.makeText(this@Register_1, response.body()!!.Data.user.first_name + "successful", Toast.LENGTH_LONG).show()
                 }catch (ex: Exception){
                     Toast.makeText(this@Register_1, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
                 }

                 val intent= Intent(this@Register_1,ProfileActivity::class.java)
                 startActivity(intent)
                 //Shared Preferences for token
                 myShared=getSharedPreferences("myShared",0)
                 var editor: SharedPreferences.Editor=myShared!!.edit()
                 editor.putString("token",response.body()!!.Data.token)
//                 editor.putString("name",name)
//                 editor.putString("profile",response.body()!!.Data.user.profile_path)
                 editor.commit()
             }

             override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                 Toast.makeText(this@Register_1,"eeeeeeeror\n"+t.message, Toast.LENGTH_LONG).show()
             }

         })
     }

}