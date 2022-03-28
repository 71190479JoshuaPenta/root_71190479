package com.example.pertemuan7_71190479

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (var listContact: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    class ContactHolder(val view:View): RecyclerView.ViewHolder(view){
        var Contact: Contact?=null
        fun bindView(contact:Contact){
            this.Contact=contact
            view.findViewById<ImageView>(R.id.imageView3).setImageResource(contact.icon)
            view.findViewById<TextView>(R.id.TextNama).text=contact.name
            view.setOnClickListener {
                val i = Intent(view.context,ContactDetail::class.java)
                i.putExtra("name", contact.name)
                i.putExtra("noHp", contact.noHp)
                i.putExtra("email", contact.email)
                view.context.startActivity(i)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactHolder(v)

    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindView(listContact[position])

    }

    override fun getItemCount(): Int= listContact.size

}
