package com.yuvrajdeshmukh.udghosh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solution)

        val answer : TextView = findViewById(R.id.answerinanswer)
        val submit : TextView = findViewById(R.id.submitbuttoninanswer)

        val auth = FirebaseAuth.getInstance()

        submit.setOnClickListener {
            val map : HashMap<String,Any> = HashMap()
            map["Solution"] = answer.text.toString()

            FirebaseDatabase.getInstance().getReference().child(auth.currentUser?.uid!!).updateChildren(map)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Thanx For Your Response",Toast.LENGTH_SHORT).show()
                        Toast.makeText(this,"Your Solution Will Help Millions",Toast.LENGTH_SHORT).show()
                    }else{
                       dialogbox("Error!!!" ,"Error Submitting Response " + it.exception?.message)
                    }
                }
        }
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