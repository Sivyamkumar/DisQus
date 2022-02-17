package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        Handler().postDelayed({
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
        },3000)
    }

    override fun onBackPressed() {
        val alertdialog : AlertDialog = AlertDialog.Builder(this).create()
        alertdialog.setTitle("Exit")
        alertdialog.setMessage("Are you sure want to exit?")

        alertdialog.setButton(AlertDialog.BUTTON_POSITIVE,"yes"){
                dialog,which -> finishAffinity()
            dialog.dismiss()
        }

        alertdialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No"){
                dialog,which ->
            dialog.dismiss()
        }
        alertdialog.show()
    }
}