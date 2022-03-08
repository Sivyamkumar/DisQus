package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    lateinit var userArrayList : ArrayList<User>
    lateinit var  recyclerView : RecyclerView
    lateinit var pb : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calling : ImageView = findViewById(R.id.call)
        val vcall : ImageView = findViewById(R.id.vc)
        val about : ImageView = findViewById(R.id.profile)


         recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        userArrayList= arrayListOf<User>()

        getuserdata()

        val addquestion : ImageView = findViewById(R.id.addquestion)

        addquestion.setOnClickListener {
            val intent = Intent(this,questiondetail::class.java)
            startActivity(intent)
        }

        calling.setOnClickListener {
            val intent = Intent(this,call::class.java)
            startActivity(intent)
        }

        vcall.setOnClickListener {
            val intent = Intent(this,vc::class.java)
            startActivity(intent)
        }

        about.setOnClickListener {
            val intent = Intent(this,profile::class.java)
            startActivity(intent)
        }

        recyclerView.setOnClickListener {
            val  intent =Intent(this,Answer::class.java)
            startActivity(intent)
        }
    }

    private fun getuserdata() {
        val  database = FirebaseDatabase.getInstance().getReference("User")
        pb= findViewById(R.id.pbar)

        pb.visibility = View.VISIBLE

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(usersnapshot in snapshot.children){

                        val user = usersnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }

                    recyclerView.adapter = MyAdapter(userArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        pb.visibility = View.GONE
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