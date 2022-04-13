package com.example.myapplication_k

//import API.Response
import API.Shop
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_recycler_item.view.*

class HomeRecyclerView(var shopList: List<Shop>) :
    RecyclerView.Adapter<HomeRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerView.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: HomeRecyclerView.ViewHolder, position: Int) {
        val responseShop : Shop = shopList[position]
        //data
        holder.PtotuctTxtName.text= shopList[position].shop_name
        Picasso.get().load(responseShop.profile_path).into(holder.ProfilePhoto)
        holder.itemView.setOnClickListener {

            val context=holder.title.context

            var intent=Intent(context,ShopActivity::class.java)

            intent.putExtra("Id",responseShop.shop_id)
            intent.putExtra("photo",responseShop.profile_path)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int { return shopList.size }

     public fun filterList(filterList:ArrayList<Shop>){
         shopList =filterList
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val PtotuctTxtName=itemView.ptotuct_txtName as TextView
        val ProfilePhoto=itemView.filt_photo as ImageView
        val title: TextView = itemView.ptotuct_txtName
    }
}