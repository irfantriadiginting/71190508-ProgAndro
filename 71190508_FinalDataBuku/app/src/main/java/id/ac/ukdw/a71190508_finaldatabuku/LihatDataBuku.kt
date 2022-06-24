package id.ac.ukdw.a71190508_finaldatabuku

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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LihatDataBuku: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihatdatabuku)

        val db = Firebase.firestore

        val btnMenu = findViewById<Button>(R.id.bt_menu)

        btnMenu.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

        db.collection("Buku")
            .get()
            .addOnSuccessListener { hasilPencarian->

                val listBuku = ArrayList<Buku>()

                for (document in hasilPencarian){
                    listBuku.add(Buku("${document.data["judulBuku"]}","${document.data["namaPenulis"]}", "${document.data["namaPenerbit"]}", "${document.data["tahunTerbit"]}", "${document.data["jumlahHalaman"]}"))
                }

                //siapkan RecyclerView
                val rvBuku = findViewById<RecyclerView>(R.id.rvListBuku)
                rvBuku.layoutManager = LinearLayoutManager (this)
                val adapter = BukuAdapter(listBuku)
                rvBuku.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Gagal", "Pengambilan Dokumen Gagal")
            }

    }
}