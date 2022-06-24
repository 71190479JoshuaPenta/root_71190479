package com.example.a71190479_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnPrevious = findViewById<Button>(R.id.btnPrev)
        btnLogout.setOnClickListener {
            val logout = Intent(this, LoginActivity::class.java)
            startActivity(logout)

        }
        btnPrevious.setOnClickListener {
            val prev = Intent(this, MainActivity::class.java)
            startActivity(prev)
        }
    }

}