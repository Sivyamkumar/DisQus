package com.yuvrajdeshmukh.udghosh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class call : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.call)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}