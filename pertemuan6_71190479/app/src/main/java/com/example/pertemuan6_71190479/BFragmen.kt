package com.example.pertemuan6_71190479

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class BFragmen:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragmen_b,container, false)
        val buttonB= v.findViewById<Button>(R.id.buttonB)
        buttonB.setOnClickListener {
            val i = Intent(context, Third_Activity::class.java)
            context?.startActivity(i)
            Toast.makeText(context, "Showing Fragment C", Toast.LENGTH_SHORT).show()
        }
        return v
    }
}