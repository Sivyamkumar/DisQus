package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


class forgetpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpassword)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ActionBar.DISPLAY_HOME_AS_UP


        val email : EditText = findViewById(R.id.email)
        val reset : Button = findViewById(R.id.reset)
        val loginbutton : TextView = findViewById(R.id.loginbutton)
        val signup : TextView = findViewById(R.id.signup)
        val progressbar : ProgressBar = findViewById(R.id.ProgressBar)

        val auth = FirebaseAuth.getInstance()

        progressbar.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
        loginbutton.setOnClickListener {
            progressbar.visibility = View.VISIBLE
            val intent =Intent(this,Login::class.java)
            startActivity(intent)
            progressbar.visibility = View.GONE
        }

        signup.setOnClickListener {
            progressbar.visibility = View.VISIBLE
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
            progressbar.visibility = View.GONE
        }

        reset.setOnClickListener {
            progressbar.visibility = View.VISIBLE
            if(TextUtils.isEmpty(email.text.toString())){
                progressbar.visibility = View.GONE
                dialogbox("Warning!!!","Email Can't be Empty")
                return@setOnClickListener
            }
            if(!email.text.toString().endsWith(".com")){
                progressbar.visibility = View.GONE
                dialogbox("Warning!!!","Invalid Email Address")
                return@setOnClickListener
            }else{
                auth.sendPasswordResetEmail(email.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            progressbar.visibility = View.GONE
                            dialogbox("Warning!!!","Check Your Email For Reset.")
                        }else{
                            progressbar.visibility = View.GONE
                            val error = "Error "+ it.exception?.message
                            dialogbox("Warning!!!",error)
                        }
                    }
            }
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

    public fun dialogbox(str1:String , str2:String){
        val alertdialog : AlertDialog = AlertDialog.Builder(this).create()
        alertdialog.setTitle(str1)
        alertdialog.setMessage(str2)

        alertdialog.setButton(AlertDialog.BUTTON_NEUTRAL,"ok"){
                dialog,which ->
            dialog.dismiss()
        }

        alertdialog.show()
    }

}