package com.example.myapplication_k

import android.app.Activity
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocal()
        setContentView(R.layout.activity_profile)


        supportFragmentManager.beginTransaction()
            .replace(R.id.Frag_container, HomeFragment()).commit()
        navibotton.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.Frag_container,HomeFragment()).commit()
                }
                R.id.order -> {
                }
                R.id.menu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.Frag_container,ProfileFragment()).commit()
                }
                R.id.supermarket -> {
                }
            }
            true


        }

    }
    // change language
    fun setLocale(lang: String)
    {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        // save data to shared
        val editor: SharedPreferences.Editor? =getSharedPreferences("myShared", MODE_PRIVATE).edit()
        editor?.putString("My_Lang", lang)
        editor?.apply()
    }

    fun loadLocal()
    {
        val sp: SharedPreferences =getSharedPreferences("myShared", Activity.MODE_PRIVATE)
        val language: String? =sp?.getString("My_Lang", "")
        setLocale(language!!)

    }
}