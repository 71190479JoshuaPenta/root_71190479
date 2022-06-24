package com.example.a71190479_final

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    var firestore: FirebaseFirestore? = null
    var listMusik = arrayListOf<Musik>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        //variable untuk pencarian
        val btnCari = findViewById<ImageButton>(R.id.btnCari)
        val edtCari = findViewById<EditText>(R.id.edtSearch)
        val rvMusik = findViewById<RecyclerView>(R.id.rvMusik)
        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)


        btnAdd.setOnClickListener {
            val i = Intent(this, TambahActivity::class.java)
            startActivity(i)
        }



        val loading = ProgressDialog(this)
        loading.setMessage("Loading Data...")
        loading.show()
        firestore?.collection("musik")?.get()?.addOnSuccessListener { docs ->
            var hasil = ""
            for (doc in docs) {
                hasil += "${doc["judul"]}"
                val musikAdd = Musik(
                    "${doc["judul"]}",
                    "${doc["penyanyi"]}",
                    "${doc["album"]}",
                    "${doc["genre"]}",
                    "${doc["tahun"]}"
                )
                listMusik.add(musikAdd)
            }
            loading.dismiss()
        }
        Handler().postDelayed({
            rvMusik.layoutManager = LinearLayoutManager(this)
            val adapter = MusikAdapter(listMusik, this)
            rvMusik.adapter = adapter
        }, 1000)

        btnCari.setOnClickListener {
            var pencarian = edtCari.text.toString()
            if (pencarian.isEmpty()) {
                Toast.makeText(this, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    rvMusik.layoutManager = LinearLayoutManager(this)
                    val adapter = MusikAdapter(listMusik, this)
                    rvMusik.adapter = adapter
                }, 1000)
            } else if (!pencarian.isEmpty()) {
                loading.setMessage("Mencari...")
                loading.show()
                listMusik.clear()
                firestore?.collection("musik")?.get()?.addOnSuccessListener { docs ->
                    for (cari in docs) {
                        var bool = true
                        val musikCari = Musik(
                            "${cari["judul"]}",
                            "${cari["penyanyi"]}",
                            "${cari["album"]}",
                            "${cari["genre"]}",
                            "${cari["tahun"]}"
                        )
                        if (pencarian.equals("${cari["judul"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listMusik.add(musikCari)
                        }
                        if (pencarian.equals("${cari["genre"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listMusik.add(musikCari)
                        }
                        if (pencarian.equals("${cari["namaProduser"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listMusik.add(musikCari)
                        }
                        if (pencarian.equals("${cari["namaPemeranUtama"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listMusik.add(musikCari)
                        }
                        if (pencarian.equals("${cari["tahun"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listMusik.add(musikCari)
                        }
                    }
                }
                loading.dismiss()
                Handler().postDelayed({
                    rvMusik.layoutManager = LinearLayoutManager(this)
                    val adapter = MusikAdapter(listMusik, this)
                    rvMusik.adapter = adapter
                },1000)
            }
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId){
            R.id.ic_home -> {
                val home = Intent(this, MainActivity::class.java)
                startActivity(home)
                this.finish()
            }
            R.id.ic_profile -> {
                val profil = Intent(this, ProfileActivity::class.java)
                startActivity(profil)
                this.finish()
            }


        }
        return super.onOptionsItemSelected(item)
    }
}