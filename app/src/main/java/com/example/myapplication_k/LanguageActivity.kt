package com.example.myapplication_k

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*


class LanguageActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocal()
        setContentView(R.layout.activity_language)

        val actionBar = getSupportActionBar()
        actionBar?.setTitle(resources.getString(R.string.app_name))

        eng_radioButton.setChecked(Update("JP_EN")!!)
        arabic_radioButton.setChecked(Update("JP_AR")!!)

    }


    fun saveInShared(key: String, value: Boolean)
    {
        var sp:SharedPreferences=getSharedPreferences("myShared", MODE_PRIVATE)
        var editor:SharedPreferences.Editor=sp?.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun Update(key: String):Boolean?{
        var sp:SharedPreferences=getSharedPreferences("myShared", MODE_PRIVATE)
        return sp.getBoolean(key, false)
    }

    //radio Button
    @SuppressLint("ResourceAsColor")
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            val i:Int
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.eng_radioButton ->
                    if (checked) {

                        saveInShared("JP_EN", checked)
                        english_txt.setTextColor(R.color.teal_200)
                        arabic_txt.setTextColor(R.color.black)
                        setLocale("en")
                        recreate()

                    }
                R.id.arabic_radioButton ->
                    if (checked) {

                        saveInShared("JP_AR", checked)
                        arabic_txt.setTextColor(R.color.teal_200)
                        english_txt.setTextColor(R.color.black)
                        setLocale("ar")
                        recreate()
                    }
            }
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



//    load language save in shared
    fun loadLocal()
    {
        val sp:SharedPreferences=getSharedPreferences("myShared", Activity.MODE_PRIVATE)
        val language: String? =sp?.getString("My_Lang", "")
        setLocale(language!!)

    }

//    fun loadLocale() {
//        val langPref = "Language"
//        val prefs = getSharedPreferences("JP_TECH",
//                MODE_PRIVATE)
//        val language = prefs.getString(langPref, "")
//        changeLang(language!!)
//    }
//
//    fun changeLang(lang: String) {
//        if (lang.equals("", ignoreCase = true)) return
//        var myLocale = Locale(lang)
//        saveLocale(lang)
//        Locale.setDefault(myLocale)
//        val config = Configuration()
//        config.locale = myLocale
//        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
//    }
//
//    fun saveLocale(lang: String?) {
//        val langPref = "Language"
//        val prefs = getSharedPreferences("JP_TECH",
//                Activity.MODE_PRIVATE)
//        val editor = prefs.edit()
//        editor.putString(langPref, lang)
//        editor.commit()
//    }


}