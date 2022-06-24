package com.example.a71190479_final

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a71190479_final.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.tvToRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()

            if (email.isEmpty()) {
                binding.emailEt.error = "Email Harus Diisi"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.error = "Email Tidak Valid"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passET.error = "Password Harus Diisi"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)

        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}