package com.example.final_datapenduduk_71190429

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class CariDataPenduduk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_data_penduduk)



        val nama = findViewById<EditText>(R.id.et_nama)

        val noKTPPenduduk = findViewById<TextView>(R.id.tv_noKTP)
        val namaPenduduk = findViewById<TextView>(R.id.tv_nama)
        val noHPPenduduk = findViewById<TextView>(R.id.tv_noHP)
        val alamatPenduduk = findViewById<TextView>(R.id.tv_alamat)


        val btnCari = findViewById<Button>(R.id.bt_cari)
        val btnBack = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnCari.setOnClickListener {
            firestore.collection("Penduduk")
                .whereEqualTo("pendudukNama", nama.text.toString())
                .get()
                .addOnSuccessListener {

                    for (document in it){
                        noKTPPenduduk.setText("${document.data["pendudukKTP"]}")
                        namaPenduduk.setText("${document.data["pendudukNama"]}")
                        noHPPenduduk.setText("${document.data["pendudukHP"]}")
                        alamatPenduduk.setText("${document.data["pendudukAlamat"]}")
                    }
                    Toast.makeText(this,"Pencarian ditemukan", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Log.d("Gagal", "Data yang dicari tidak ditemukan")
                }
        }

        btnBack.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

    }
}