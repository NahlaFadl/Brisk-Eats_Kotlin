package com.example.myapplication_k

import API.ApiInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_account_setting.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountSettingFragment : Fragment() {

    var myShared: SharedPreferences?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_account_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        myShared=context?.getSharedPreferences("myShared",0)

        // editProfile
        editProfile.setOnClickListener {
            val intent= Intent(activity,Register::class.java)
            startActivity(intent)
        }

        //terms And condition
        txt_abutAs.setOnClickListener {
            var intent= Intent(activity,AboutUsActivity::class.java)
            startActivity(intent)
        }

        //logout
        logout_txt.setOnClickListener {
            Toast.makeText(activity,"llllogggoutt",Toast.LENGTH_SHORT).show()
            logout()
        }

        //language
        language_txt.setOnClickListener {
            var intent=Intent(activity,LanguageActivity::class.java)
            startActivity(intent)
        }
    }

    fun logout() {

        val retrfit= Retrofit.Builder()
            .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface =retrfit.create(ApiInterface::class.java)
        val call=api.logOut(myShared!!.getString("token",""),"mobile")
        call.enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                myShared!!.edit()!!.clear()!!.commit()
                try {
                    Toast.makeText(activity, response.message()+ " succe", Toast.LENGTH_LONG).show()
                }catch (ex: Exception){
                    Toast.makeText(activity, "change (Email) OR (Mobile Number) \n "+ex.message, Toast.LENGTH_LONG).show()
                }
                var intent= Intent(activity,LoginActivity::class.java)
                startActivity(intent)

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(activity,t.message, Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment FavouritFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountSettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}