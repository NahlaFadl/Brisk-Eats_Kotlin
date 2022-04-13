package com.example.myapplication_k

import API.ApiInterface
import API.Products
import API.Shop
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.IDN

class ShopActivity: AppCompatActivity() {
    var ID:Int?=null
    var photo:String?=null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        ID=intent?.extras?.getInt("Id")
        photo=intent?.extras?.getString("photo")
        Picasso.get().load(photo).into(profile_shop)
        getProd()
        pro_recycler.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
    }

    fun getProd(){

        val retrofit=Retrofit.Builder()
                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api:ApiInterface=retrofit.create(ApiInterface::class.java)
        val call=api.getProduct(ID!!)
        call.enqueue(object:Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {

                var pp: Int? = response.body()?.Data?.size
                var nn: ArrayList<Products> = ArrayList()

                for (i in 1..pp!!) {
                    nn.add(response.body()!!)
                }
                val adapter = ProductRecycler(nn!!)
                pro_recycler.adapter = adapter
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@ShopActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }
}