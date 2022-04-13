package com.example.myapplication_k

import API.ApiInterface
import API.ResponseTerms
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about_us.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        getTermsAndCondition()
        back.setOnClickListener {
//            var it=Intent(this,ProfileFragment::class.java)
//            startActivity(it)

        }
    }

    fun getTermsAndCondition() {

        val retrfit= Retrofit.Builder()
            .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
        val call=api.getTerms()
        call.enqueue(object : Callback<ResponseTerms>
        {
            override fun onResponse(call: Call<ResponseTerms>, response: Response<ResponseTerms>) {
                try {
                    //data
                    body_txt.text=response.body()!!.Data.page_body
                }catch (ex: Exception){
                    Toast.makeText(this@AboutUsActivity, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<ResponseTerms>, t: Throwable) {
                Toast.makeText(this@AboutUsActivity,t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}