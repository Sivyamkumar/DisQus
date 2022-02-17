package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val emailid : EditText = findViewById(R.id.emailid)
        val pswd : EditText = findViewById(R.id.Password)
        val loginbtn : Button = findViewById(R.id.login_btn)
        val forgetActivity : TextView = findViewById(R.id.forgetactivity)
        val registerActivity : TextView = findViewById(R.id.registeractivity)
        val progressBar2 : ProgressBar = findViewById(R.id.progressbar2)

        val auth = FirebaseAuth.getInstance()
        progressBar2.visibility = View.INVISIBLE

        forgetActivity.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val intent =Intent(this,forgetpassword::class.java)
            startActivity(intent)
            progressBar2.visibility = View.INVISIBLE
        }

        registerActivity.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val intent =Intent(this,Register::class.java)
            startActivity(intent)
            progressBar2.visibility = View.INVISIBLE
        }

        loginbtn.setOnClickListener {
            progressBar2.visibility = View.VISIBLE

            if(TextUtils.isEmpty(emailid.text.toString()) || TextUtils.isEmpty(pswd.text.toString())){
                progressBar2.visibility = View.INVISIBLE
                dialogbox("Warning!!!","Invalid User credentials")
                return@setOnClickListener
            }
            if(pswd.text.toString().length < 6){
                progressBar2.visibility = View.INVISIBLE
                dialogbox("Warning!!!","Password Must Have 6 characters")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailid.text.toString(),pswd.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        if(auth.currentUser?.isEmailVerified!!){
                            val intent =Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            progressBar2.visibility = View.INVISIBLE
                        }else{
                            progressBar2.visibility = View.INVISIBLE
                            dialogbox("Warning!!!","Please Verify Your Email First.")
                        }
                    }else{
                        progressBar2.visibility = View.INVISIBLE
                        val error = "Unexpected Error !!!" + it.exception?.message
                        dialogbox("Warning!!!",error)
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