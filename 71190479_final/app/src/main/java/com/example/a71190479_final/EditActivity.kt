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

class EditActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        val getJudul = intent.getStringExtra("judul").toString()
        val getPenyanyi= intent.getStringExtra("penyanyi").toString()
        val getAlbum = intent.getStringExtra("album").toString()
        val getGenre = intent.getStringExtra("genre").toString()
        val getTahun = intent.getStringExtra("tahun").toString()

        val edtJudul = findViewById<EditText>(R.id.edtUpdateJudul)
        val edtPenyanyi = findViewById<EditText>(R.id.edtUpdatePenyanyi)
        val edtAlbum= findViewById<EditText>(R.id.edtUpdateJudulAlbum)
        val edtGenre= findViewById<EditText>(R.id.edtUpdateGenre)
        val edtTahun = findViewById<EditText>(R.id.edtUpdateTahun)

        val btnSave = findViewById<Button>(R.id.btnSave)

        edtJudul.setText(getJudul)
        edtPenyanyi.setText(getPenyanyi)
        edtAlbum.setText(getAlbum)
        edtGenre.setText(getGenre)
        edtTahun.setText(getTahun)

        btnSave.setOnClickListener {
            val updateMusik = Musik(
                edtJudul.text.toString(),
                edtPenyanyi.text.toString(),
                edtAlbum.text.toString(),
                edtGenre.text.toString(),
                edtTahun.text.toString()
            )
            firestore?.collection("musik")?.whereEqualTo("judul", getJudul)?.get()!!
                .addOnSuccessListener {
                    for (update in it) {
                        firestore?.collection("musik")?.document(update.id)?.set(updateMusik)
                            ?.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                }
                            }
                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}