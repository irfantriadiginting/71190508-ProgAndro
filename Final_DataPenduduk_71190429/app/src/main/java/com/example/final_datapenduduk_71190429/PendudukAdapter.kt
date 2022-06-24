package com.example.final_datapenduduk_71190429

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import androidx.core.content.ContextCompat.startActivity




class PendudukAdapter (val listPenduduk: ArrayList<Penduduk>): RecyclerView.Adapter<PendudukAdapter.PendudukHolder>(){
    class PendudukHolder(val v: View): RecyclerView.ViewHolder(v){
        var penduduk: Penduduk? = null
        val firestore = FirebaseFirestore.getInstance()

        fun bindView(penduduk: Penduduk){
            this.penduduk = penduduk
            v.findViewById<TextView>(R.id.tv_noKTP).text = penduduk.pendudukKTP
            v.findViewById<TextView>(R.id.tv_nama).text = penduduk.pendudukNama
            v.findViewById<TextView>(R.id.tv_noHP).text = penduduk.pendudukHP
            v.findViewById<TextView>(R.id.tv_alamat).text = penduduk.pendudukAlamat


            //button delete
            v.findViewById<Button>(R.id.bt_delete).setOnClickListener{

                firestore.collection("Penduduk")
                    .whereEqualTo("pendudukKTP", penduduk.pendudukKTP)
                    .get()
                    .addOnSuccessListener {
                        for (document in it){
                            firestore.collection("Penduduk").document(document.id).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(v.context,"Data Berhasil dihapus",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(v.context,"Data Gagal dihapus",Toast.LENGTH_SHORT).show()
                    }
                (v.context as Activity).recreate()
            }

            //button edit
            v.findViewById<Button>(R.id.bt_edit).setOnClickListener{

                val i = Intent(v.context, EditDataPenduduk::class.java)
                i.putExtra("noKTP",penduduk.pendudukKTP)
                i.putExtra("nama",penduduk.pendudukNama)
                i.putExtra("noHP",penduduk.pendudukHP)
                i.putExtra("alamat",penduduk.pendudukAlamat)
                v.context.startActivity(i)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendudukAdapter.PendudukHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_penduduk, parent, false)
        return PendudukHolder(v)
    }
    //memilih file layout XML yang akan dijadikan container

    override fun onBindViewHolder(holder: PendudukAdapter.PendudukHolder, position: Int) {
        //memasang data ke dalam file layout XML yang telah dipilih
        holder.bindView(listPenduduk[position])
    }

    override fun getItemCount(): Int {
        //mengembalikan jumlah item yang terdapat pada RecyclerView
        return listPenduduk.size
    }

}
