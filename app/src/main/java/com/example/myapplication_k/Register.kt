package com.example.myapplication_k

import API.ApiInterface
import API.ResponseRegister
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_1.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*


class Register : AppCompatActivity() {

    val MY_PERMISSIONS_REQUEST_LOCATION= 99
    val CAPTURE_REQUEST_CODE = 0
    val SELECT_REQUEST_CODE =1
    lateinit var api : ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://50.116.98.202/~briskdrive/brisk_delivery/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        api= retrofit.create(ApiInterface::class.java)

        //to upload photo to api
        done.setOnClickListener {
            uploadToApi()
        }

        // update profile
        profile_image.setOnClickListener {
            if (CheckPermission())
            {

                // take photo by camera
//                var capture =  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(capture, CAPTURE_REQUEST_CODE)

                //select photo by gallery
                val select = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(select, SELECT_REQUEST_CODE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    val bitmap = data!!.extras!!["data"] as Bitmap?
//                    imageView.setImageBitmap(bitmap)
//                    progressDialog.show()
                    ImageUpload(bitmap!!)
                }
            }
            SELECT_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    try {
                        val ImageUri: Uri? = data!!.data
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, ImageUri)
                        profile_image.setImageBitmap(bitmap)
                        //  progressDialog.show()
                        ImageUpload(bitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }

    private fun ImageUpload(bitmap: Bitmap) {

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val profile: String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        val name: String = java.lang.String.valueOf(Calendar.getInstance().getTimeInMillis())

//        var st_frstName= reg_name.text.toString()
//        var st_lastName= reg_name.text.toString()
//        var st_email= reg_email.text.toString()
//        var st_password= reg_password.text.toString()
//        var st_number= reg_phon.text.toString()
//
//
////        progressDialog = ProgressDialog(this)
////        progressDialog.setMessage("Image Upload....")
//
//        val call: Call<RegisterData> = api.updateProfile(profile,st_frstName,st_lastName,st_email,st_password,st_number)
//        call.enqueue(object : Callback<RegisterData> {
//            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
//                Toast.makeText(this@Register, "" + response.message(), Toast.LENGTH_SHORT).show()
//                // progressDialog.dismiss()
//            }
//
//            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
//                Toast.makeText(this@Register, "Failed to Upload", Toast.LENGTH_SHORT).show()
//                //progressDialog.dismiss()
//            }
//        })

    }

    private fun uploadToApi(){


        val byteArrayOutputStream = ByteArrayOutputStream()
       // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val profile: String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        val name: String = java.lang.String.valueOf(Calendar.getInstance().getTimeInMillis())
        var st_frstName= reg_name.text.toString()
        var st_lastName= reg_name.text.toString()
        var st_email= reg_email.text.toString()
        var st_password= reg_password.text.toString()
        var st_number= reg_phon.text.toString()


//        progressDialog = ProgressDialog(this)
//        progressDialog.setMessage("Image Upload....")

        val call: Call<ResponseRegister> = api.updateProfile(profile,st_frstName,st_lastName,st_email,st_password,st_number)
        call.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                Toast.makeText(this@Register, "" + response.message(), Toast.LENGTH_SHORT).show()
                // progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Toast.makeText(this@Register, "Failed to Upload", Toast.LENGTH_SHORT).show()
                //progressDialog.dismiss()
            }
        })
    }

    fun CheckPermission(): Boolean {
        return if ((ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok") { dialogInterface, i -> //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                    MY_PERMISSIONS_REQUEST_LOCATION)
                            startActivity(Intent(this, Register()::class.java))
                            this.overridePendingTransition(0, 0)
                        }
                        .create()
                        .show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            false
        } else {
            true
        }
    }

}