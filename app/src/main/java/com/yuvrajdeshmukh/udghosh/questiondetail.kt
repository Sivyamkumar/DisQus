package com.yuvrajdeshmukh.udghosh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.NonCancellable.children

class questiondetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questiondetail)

        val titletxt: EditText = findViewById(R.id.Title)
        val questiontxt: EditText = findViewById(R.id.questioninput)
        val postquestion: Button = findViewById(R.id.post)
        val name: EditText = findViewById(R.id.nametxt)

        val auth = FirebaseAuth.getInstance()

        postquestion.setOnClickListener {
            val map: HashMap<String, Any> = HashMap()
            map["Question"] = questiontxt.text.toString()
            map["Title"] = titletxt.text.toString()

            FirebaseDatabase.getInstance().getReference().child("User").child(name.text.toString()).updateChildren(map)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Question Submitted Successfully",Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this,it.exception?.message,Toast.LENGTH_LONG).show()
                    }
                }
        }



    }
}