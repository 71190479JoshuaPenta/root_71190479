package com.example.pertemuan5_71190479

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.button1)
        btnLogin.setOnClickListener {
            login(etUsername.text.toString(), etPassword.text.toString())
        }
    }
    fun login(username: String, password: String) {
        if (password.equals("1234")){
            val i: Intent = Intent(this, DashboardActivity::class.java)
            i.putExtra("user", username)
            startActivity(i)
        }else{
            Toast.makeText(this, "Password Salah", Toast.LENGTH_LONG).show()
        }
    }
}