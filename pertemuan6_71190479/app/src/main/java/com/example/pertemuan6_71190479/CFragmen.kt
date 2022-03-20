package com.example.pertemuan6_71190479

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CFragmen:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragmen_c ,container, false)
        val buttonC = v.findViewById<Button>(R.id.buttonC)
        buttonC.setOnClickListener {
            val i = Intent(context, MainActivity::class.java)
            context?.startActivity(i)
            Toast.makeText(context, "Showing first page", Toast.LENGTH_SHORT).show()

        }
        return v
    }
}