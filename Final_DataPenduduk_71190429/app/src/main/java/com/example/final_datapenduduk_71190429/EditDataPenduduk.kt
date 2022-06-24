package com.example.final_datapenduduk_71190429

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.common.net.InternetDomainName
import com.google.firebase.firestore.FirebaseFirestore

class EditDataPenduduk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penduduk_edit)

        val noKTP = getIntent().getStringExtra("noKTP")
        val nama = getIntent().getStringExtra("nama")
        val noHP = getIntent().getStringExtra("noHP")
        val alamat = getIntent().getStringExtra("alamat")

        val noKTPPenduduk = findViewById<TextView>(R.id.tv_noKTP)
        noKTPPenduduk.setText("${noKTP}")

        val namaPenduduk = findViewById<EditText>(R.id.et_nama)
        namaPenduduk.setText("${nama}")

        val noHPPenduduk = findViewById<EditText>(R.id.et_noHP)
        noHPPenduduk.setText("${noHP}")

        val alamatPenduduk = findViewById<EditText>(R.id.et_alamat)
        alamatPenduduk.setText("${alamat}")

        val btnEdit = findViewById<Button>(R.id.bt_edit)
        val btnBack = findViewById<Button>(R.id.bt_back)
        val firestore = FirebaseFirestore.getInstance()

        btnEdit.setOnClickListener {
            firestore.collection("Penduduk")
                    .whereEqualTo("pendudukKTP", noKTPPenduduk.text.toString())
                    .get()
                    .addOnSuccessListener {
                        for (document in it){

                            firestore.collection("Penduduk").document(document.id)
                                .update("pendudukNama",namaPenduduk.text.toString(),"pendudukHP",noHPPenduduk.text.toString(),"pendudukAlamat",alamatPenduduk.text.toString())
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Data berhasil diubah",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener{
                        Log.d("Gagal", "Data gagal diubah")
                    }
        }

        btnBack.setOnClickListener { 
            val i = Intent(this, LihatDataPenduduk::class.java)
            startActivity(i)
        }
    }
}