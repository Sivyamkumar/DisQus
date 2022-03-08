package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.yuvrajdeshmukh.udghosh.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class Register : AppCompatActivity() {

    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        TextView
        val nametext : EditText = findViewById(R.id.name)
        val emailId : EditText = findViewById(R.id.emailId)
        val phone : EditText = findViewById(R.id.mobile)
        val pswd : EditText = findViewById(R.id.password)
        val cnfpswd : EditText = findViewById(R.id.cnfpassword)
        val signin : Button = findViewById(R.id.signin)
        val progressBar : ProgressBar = findViewById(R.id.progressbar3)

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance().reference
        val register : Button = findViewById(R.id.register)

        progressBar.visibility = View.GONE

        register.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if(TextUtils.isEmpty(emailId.text.toString())|| TextUtils.isEmpty(nametext.text.toString()) || TextUtils.isEmpty(phone.text.toString()) ||
                TextUtils.isEmpty(pswd.text.toString()) || TextUtils.isEmpty(cnfpswd.text.toString())){
                dialogbox("Warning!!!","All Credentials Must Be Filled")
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if(!emailId.text.toString().endsWith(".com")){
                dialogbox("Warning!!!","Invalid EmailID")
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if(pswd.text.toString() != cnfpswd.text.toString()){
                dialogbox("Warning!!!","Passwords don't match")
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if(pswd.text.toString().length < 8){
                dialogbox("Warning!!!","Password Must be of 8 characters.")
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            else{
                auth.createUserWithEmailAndPassword(emailId.text.toString(),pswd.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            auth.currentUser?.sendEmailVerification()
                            progressBar.visibility = View.GONE
                            dialogbox("Warning!!!","Verify Your Email.")
                            progressBar.visibility = View.VISIBLE

                            val map: HashMap<String, Any> = HashMap()

                            name = nametext.text.toString()
                            map["Name"] = name
                            map["Mobile"] = phone.text.toString()
                            map["Password"] = pswd.text.toString()
                            map["Email"] = emailId.text.toString()

                            FirebaseDatabase.getInstance().getReference().child("User").child(name).updateChildren(map)
                                .addOnCompleteListener {
                                    if(it.isSuccessful){
                                        nametext.text.clear()
                                        emailId.text.clear()
                                        phone.text.clear()
                                        pswd.text.clear()
                                        cnfpswd.text.clear()
                                        progressBar.visibility = View.GONE
                                        Toast.makeText(this,"Successful Registered",Toast.LENGTH_LONG).show()
                                    }else{
                                        Toast.makeText(this,it.exception?.message,Toast.LENGTH_LONG).show()
                                        progressBar.visibility = View.GONE
                                    }
                                }
                        }else{
                            val error = it.exception?.message
                            dialogbox("Failed",""+error)
                        }
                    }
            }


        }

        signin.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
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


    private fun senddata(){

    }

}