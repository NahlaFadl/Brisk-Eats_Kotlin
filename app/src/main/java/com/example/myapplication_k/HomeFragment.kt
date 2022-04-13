package com.example.myapplication_k

import API.*
//import API.Response
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.text.TextWatcher as TextWatcher1

//import kotlin.collections.ArrayList


//import androidx.work.Data


//import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var api:ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofit=Retrofit.Builder()
                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        api =retrofit.create(ApiInterface::class.java)

        not_found.setVisibility(View.VISIBLE)
        rcyclerView.setVisibility(View.GONE)

        search.addTextChangedListener(object : TextWatcher1 {
            override fun afterTextChanged(s: Editable?) {

                getproduct(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })






//       val product=ArrayList<RegisterData>()
        rcyclerView.layoutManager= LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
    }



fun getproduct(text:String){

//    val retrofit=Retrofit.Builder()
//            .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    val api : ApiInterface=retrofit.create(ApiInterface::class.java)
    val call=api.getFiltration(text)
    call.enqueue(object : Callback<Filtration> {
        override fun onResponse(call: Call<Filtration>, response: Response<Filtration>) {

            if (response.isSuccessful)
            {
                if(text !="") {

                    not_found.setVisibility(View.GONE)
                    rcyclerView.setVisibility(View.VISIBLE)
                    var rcycleraddpter: HomeRecyclerView = HomeRecyclerView(response.body()!!.Data!!.shops)
                    val bh = response.body()!!.Data.shops
                    var filterList: ArrayList<Shop> = ArrayList()
                    for (name in bh) {

                        if (name.shop_name.toLowerCase().contains(text.toLowerCase()))
                            filterList.add(name)
                    }
                    rcycleraddpter.filterList(filterList)
                    rcyclerView.adapter = rcycleraddpter
                }
                else if(text =="") {
                    not_found.setVisibility(View.VISIBLE)
                    rcyclerView.setVisibility(View.GONE)
                }
            }
            else
            {
                not_found.setVisibility(View.VISIBLE)
                rcyclerView.setVisibility(View.GONE)
            }
        }

        override fun onFailure(call: Call<Filtration>, t: Throwable) {
            not_found.setVisibility(View.VISIBLE)
            rcyclerView.setVisibility(View.GONE)
            Toast.makeText(activity, "error occourant "+t.message, Toast.LENGTH_LONG).show()
        }

    })

}

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}