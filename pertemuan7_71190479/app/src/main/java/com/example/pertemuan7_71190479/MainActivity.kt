package com.example.pertemuan7_71190479

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listContact= arrayListOf<Contact>()
        listContact.add(Contact("Yakleb",R.mipmap.contact_icon,"082819138", "YaklebTabanting@gmail.com"))
        listContact.add(Contact("Yakomina",R.mipmap.contact_icon, "00824350",  "yakominaCantip@gmail.com"))
        listContact.add(Contact("Dorce",R.mipmap.contact_icon, "0829901928", "dorceUnyu@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)

        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }


}
