package com.example.pertemuan5_71190479

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val user = intent.getStringExtra("user")
        val tvUser = findViewById<TextView>(R.id.tvUser)
        tvUser.text = "Selamat Datang di Welcome ${user}"
    }
}