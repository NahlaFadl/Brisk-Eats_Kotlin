package com.example.myapplication_k

import API.Products
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_shop_item.view.*

class ProductRecycler(var productsList: List<Products>) :
        RecyclerView.Adapter<ProductRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.recycler_shop_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val responseProduct : Products = productsList[position]
        //data
        holder.ProductTxtName.text= responseProduct.Data!![position].product_name
        holder.productDesc.text=responseProduct.Data!![position].product_desc
        holder.productPrice.text=responseProduct.Data!![position].product_prices!![0].product_price_after
        holder.productCurrency.text=responseProduct.Data!![position].product_prices!![0].currency
        Picasso.get().load(responseProduct.Data!![position].profile_path).into(holder.ProfilePhoto)
        holder.itemView.setOnClickListener {
            val context=holder.title.context
            val intent=Intent(context,DetailsProductActivity::class.java)
            intent.putExtra("picture",responseProduct.Data!![position].profile_path)
            intent.putExtra("nameProduct",responseProduct.Data!![position].product_name)
            intent.putExtra("descProduct",responseProduct.Data!![position].product_desc)
            intent.putExtra("sizeProduct",responseProduct.Data!![position].product_prices!![0].product_size)
            intent.putExtra("priceProduct",responseProduct.Data!![position].product_prices!![0].product_price_after)
            intent.putExtra("productID",responseProduct.Data!![position].product_id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int { return productsList.size }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val ProductTxtName=itemView.protuct_name as TextView
        val productDesc=itemView.product_des as TextView
        val productPrice=itemView.product_price as TextView
        val productCurrency=itemView.txt_currency as TextView
        val ProfilePhoto=itemView.protect_photo as ImageView
        val title=itemView.product_des
    }
}