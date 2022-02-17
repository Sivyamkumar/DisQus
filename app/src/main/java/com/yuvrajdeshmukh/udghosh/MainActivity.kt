package com.yuvrajdeshmukh.udghosh

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.qnaFragment,
                R.id.favoriteFragment,
                R.id.settingsFragment,
                R.id.profileFragment
            )
        )
        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val questionbutton : ImageView = findViewById(R.id.question)
        questionbutton.setOnClickListener{
            val intent = Intent(this,questiondetail::class.java)
            startActivity(intent)
        }



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