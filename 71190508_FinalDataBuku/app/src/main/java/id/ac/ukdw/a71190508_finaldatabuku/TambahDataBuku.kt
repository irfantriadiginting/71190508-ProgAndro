package id.ac.ukdw.a71190508_finaldatabuku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TambahDataBuku: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahdatabuku)

        val judulBuku = findViewById<EditText>(R.id.et_judulBuku)
        val namaPenulis = findViewById<EditText>(R.id.et_penulis)
        val namaPenerbit = findViewById<EditText>(R.id.et_penerbit)
        val tahunTerbit = findViewById<EditText>(R.id.et_thnTerbit)
        val jumlahHalaman = findViewById<EditText>(R.id.et_jmlHalaman)

        val btnsubmit = findViewById<Button>(R.id.bt_submit)
        val btnback = findViewById<Button>(R.id.bt_back)

        val db = Firebase.firestore

        btnback.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java);
            startActivity(i)
        }

        btnsubmit.setOnClickListener {
            if (judulBuku.text.toString() != "" && namaPenulis.text.toString() != "" && namaPenerbit.text.toString() != "" && tahunTerbit.text.toString() != "" && jumlahHalaman.text.toString() != "") {

                val buku = Buku(
                    judulBuku.text.toString(),
                    namaPenulis.text.toString(),
                    namaPenerbit.text.toString(),
                    tahunTerbit.text.toString(),
                    jumlahHalaman.text.toString()
                )
                db.collection("Buku").add(buku)
                Toast.makeText(this, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                judulBuku.setText("")
                namaPenulis.setText("")
                namaPenerbit.setText("")
                tahunTerbit.setText("")
                jumlahHalaman.setText("")
            } else {
                Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
    }
}