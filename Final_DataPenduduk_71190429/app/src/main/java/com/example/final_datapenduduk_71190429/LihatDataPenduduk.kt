package com.example.final_datapenduduk_71190429

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class LihatDataPenduduk: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihatdatapenduduk)

        val firestore = FirebaseFirestore.getInstance()

        val btnMenu = findViewById<Button>(R.id.bt_menu)

        btnMenu.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

        firestore.collection("Penduduk")
            .get()
            .addOnSuccessListener { hasilPencarian->

                val listPenduduk = ArrayList<Penduduk>()

                for (document in hasilPencarian){
                    listPenduduk.add(Penduduk("${document.data["pendudukKTP"]}","${document.data["pendudukNama"]}", "${document.data["pendudukHP"]}", "${document.data["pendudukAlamat"]}"))
                }

                //siapkan RecyclerView
                val rvPenduduk = findViewById<RecyclerView>(R.id.rvListPenduduk)
                rvPenduduk.layoutManager = LinearLayoutManager (this)
                val adapter = PendudukAdapter(listPenduduk)
                rvPenduduk.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Gagal", "Pengambilan Dokumen Gagal")
            }

    }
}