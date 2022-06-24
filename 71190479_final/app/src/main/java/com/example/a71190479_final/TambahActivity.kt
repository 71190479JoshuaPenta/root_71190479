package com.example.a71190479_final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TambahActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()



        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtPenyanyi = findViewById<EditText>(R.id.edtPenyanyi)
        val edtAlbum = findViewById<EditText>(R.id.edtJudulAlbum)
        val edtGenre = findViewById<EditText>(R.id.edtGenre)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val musik = Musik(
                edtJudul.text.toString(), edtPenyanyi.text.toString(),
                edtAlbum.text.toString(), edtGenre.text.toString(),
                edtTahun.text.toString()
            )

            edtJudul.setText("")
            edtPenyanyi.setText("")
            edtAlbum.setText("")
            edtGenre.setText("")
            edtTahun.setText("")
            firestore?.collection("musik")?.add(musik)?.addOnSuccessListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}
