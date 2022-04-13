package com.example.myapplication_k

import API.ApiInterface
import API.PostFavorite
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsProductActivity : AppCompatActivity() {

    var productID:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)

        val txtName=intent?.extras?.getString("nameProduct")
        nameProduct.text=txtName
        val txtDesc=intent?.extras?.getString("descProduct")
        descProduct.text=txtDesc
        val productPhoto=intent?.extras?.getString("picture")
        Picasso.get().load(productPhoto).into(product_photo)
        val txtSize=intent?.extras?.getString("sizeProduct")
        product_size.text=txtSize
        val txtPrice=intent?.extras?.getString("priceProduct")
        txt_price.text=txtPrice

        productID=intent?.extras?.getInt("productID")
        fab.setOnClickListener {

            postFavoriteItem(productID!!)
            Toast.makeText(this,""+productID,Toast.LENGTH_SHORT).show()
        }
    }

    fun postFavoriteItem(productId:Int){

        val retrfit= Retrofit.Builder()
                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
        val call=api.postFavorite(productId)
        call.enqueue(object:Callback<PostFavorite>{
            override fun onResponse(call: Call<PostFavorite>, response: Response<PostFavorite>) {
                Toast.makeText(this@DetailsProductActivity,"done\n "+response.message(),Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<PostFavorite>, t: Throwable) {
                Toast.makeText(this@DetailsProductActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })

    }
}