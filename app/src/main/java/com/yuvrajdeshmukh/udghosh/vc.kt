package com.yuvrajdeshmukh.udghosh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class vc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vc)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}