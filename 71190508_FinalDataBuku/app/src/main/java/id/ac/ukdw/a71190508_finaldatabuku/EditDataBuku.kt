package id.ac.ukdw.a71190508_finaldatabuku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.common.net.InternetDomainName
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditDataBuku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buku_edit)

        val judulBuku = getIntent().getStringExtra("judulBuku")
        val namaPenulis = getIntent().getStringExtra("namaPenulis")
        val namaPenerbit = getIntent().getStringExtra("namaPenerbit")
        val tahunTerbit = getIntent().getStringExtra("tahunTerbit")
        val jumlahHalaman = getIntent().getStringExtra("jumlahHalaman")

        val judulBukuNya = findViewById<TextView>(R.id.tv_judul)
        judulBukuNya.setText("${judulBuku}")

        val namaPenulisBuku = findViewById<EditText>(R.id.et_penulis)
        namaPenulisBuku.setText("${namaPenulis}")

        val namaPenerbitBuku = findViewById<EditText>(R.id.et_penerbit)
        namaPenerbitBuku.setText("${namaPenerbit}")

        val tahunTerbitBuku = findViewById<EditText>(R.id.et_thnTerbit)
        tahunTerbitBuku.setText("${tahunTerbit}")

        val jumlahHalamanBuku = findViewById<EditText>(R.id.et_jmlHalaman)
        jumlahHalamanBuku.setText("${jumlahHalaman}")

        val btnEdit = findViewById<Button>(R.id.bt_edit)
        val btnBack = findViewById<Button>(R.id.bt_back)
        val db = Firebase.firestore

        btnEdit.setOnClickListener {
            db.collection("Buku")
                .whereEqualTo("judulBuku", judulBukuNya.text.toString())
                .get()
                .addOnSuccessListener {
                    for (document in it) {

                        db.collection("Buku").document(document.id)
                            .update(
                                "judulBuku",
                                judulBukuNya.text.toString(),
                                "namaPenulis",
                                namaPenulisBuku.text.toString(),
                                "namaPenerbit",
                                namaPenerbitBuku.text.toString()
                            )
                            .addOnSuccessListener {
                                Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }
                .addOnFailureListener {
                    Log.d("Gagal", "Data gagal diubah")
                }
        }

        btnBack.setOnClickListener {
            val i = Intent(this, LihatDataBuku::class.java)
            startActivity(i)
        }
    }
}