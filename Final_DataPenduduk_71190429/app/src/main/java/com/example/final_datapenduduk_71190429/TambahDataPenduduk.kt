package com.example.final_datapenduduk_71190429

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahDataPenduduk: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahdatapenduduk)

        val noktp = findViewById<EditText>(R.id.et_noKTP)
        val nama = findViewById<EditText>(R.id.et_nama)
        val nohp = findViewById<EditText>(R.id.et_noHP)
        val alamat = findViewById<EditText>(R.id.et_alamat)

        val btnsubmit = findViewById<Button>(R.id.bt_submit)
        val btnback = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnback.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java);
            startActivity(i)
        }

        btnsubmit.setOnClickListener {
            if (noktp.text.toString() != "" && nama.text.toString() != "" && nohp.text.toString() != "" && alamat.text.toString() != "") {

                val penduduk = Penduduk(
                    noktp.text.toString(),
                    nama.text.toString(),
                    nohp.text.toString(),
                    alamat.text.toString()
                )
                firestore.collection("Penduduk").add(penduduk)
                Toast.makeText(this, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                noktp.setText("")
                nama.setText("")
                nohp.setText("")
                alamat.setText("")
            } else {
                Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
    }
}