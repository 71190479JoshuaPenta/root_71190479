package com.example.a71190479_final

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.time.measureTimedValue

class MusikAdapter(private val musikList: ArrayList<Musik>, var context: Context): RecyclerView.Adapter<MusikAdapter.MusikHolder>() {
    inner class MusikHolder(val view: View): RecyclerView.ViewHolder(view) {
        var firestore: FirebaseFirestore? = null
        fun bind(musik: Musik, context: Context) {

            firestore = FirebaseFirestore.getInstance()

            val judul = view.findViewById<TextView>(R.id.tvJudul)
            val penyanyi = view.findViewById<TextView>(R.id.tvPenyanyi)
            val album = view.findViewById<TextView>(R.id.tvAlbum)
            val genre = view.findViewById<TextView>(R.id.tvGenre)
            val tahun = view.findViewById<TextView>(R.id.tvTahun)

            judul.setText(musik.judul)
            penyanyi.setText(musik.penyanyi)
            album.setText(musik.album)
            genre.setText(musik.genre)
            tahun.setText(musik.tahun)


            val btnEdit = view.findViewById<Button>(R.id.btnEdit)
            val btnHapus = view.findViewById<Button>(R.id.btnHapus)


            btnEdit.setOnClickListener {
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("judul", judul.text)
                intent.putExtra("penyanyi", penyanyi.text)
                intent.putExtra("album", album.text)
                intent.putExtra("genre", genre.text)
                intent.putExtra("tahun", tahun.text)
                context.startActivity(intent)
            }
            btnHapus.setOnClickListener {

                firestore?.collection("musik")?.whereEqualTo("judul",judul.text)?.get()!!.addOnSuccessListener {
                    for (hapus in it){
                        firestore?.collection("musik")?.document(hapus.id)?.delete()
                    }
                }
                musikList.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusikHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_musik,parent,false)
        return MusikHolder(v)

    }

    override fun onBindViewHolder(holder: MusikHolder, position: Int) {

        holder.bind(musikList[position],context)

    }

    override fun getItemCount(): Int {
        return musikList.size

    }


}
