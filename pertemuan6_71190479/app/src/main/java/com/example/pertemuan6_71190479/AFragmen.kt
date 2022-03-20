package com.example.pertemuan6_71190479

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AFragmen:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragmen_a ,container, false)
        val buttonA = v.findViewById<Button>(R.id.buttonA)
        buttonA.setOnClickListener {
            val i = Intent(context, Second_Activity::class.java)
            context?.startActivity(i)
            Toast.makeText(context, "Showing Fragmen B", Toast.LENGTH_SHORT).show()

        }

        return v
    }
}