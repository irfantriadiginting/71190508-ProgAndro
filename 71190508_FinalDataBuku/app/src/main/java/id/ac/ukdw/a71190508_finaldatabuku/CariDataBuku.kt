package id.ac.ukdw.a71190508_finaldatabuku
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CariDataBuku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buku_caridata)



        val judulBukuNya = findViewById<EditText>(R.id.et_judulBuku)

        val judulBuku = findViewById<TextView>(R.id.tv_judul)
        val namaPenulis = findViewById<TextView>(R.id.tv_namaPenulis)
        val namaPenerbit = findViewById<TextView>(R.id.tv_namaPenerbit)
        val tahunTerbit = findViewById<TextView>(R.id.tv_thnTerbit)
        val jumlahHalaman = findViewById<TextView>(R.id.tv_halBuku)


        val btnCari = findViewById<Button>(R.id.bt_cari)
        val btnBack = findViewById<Button>(R.id.bt_back)

        val db = Firebase.firestore

        btnCari.setOnClickListener {
            db.collection("Buku")
                .whereEqualTo("judulBuku", judulBukuNya.text.toString())
                .get()
                .addOnSuccessListener {

                    for (document in it){
                        judulBuku.setText("${document.data["judulBuku"]}")
                        namaPenulis.setText("${document.data["namaPenulis"]}")
                        namaPenerbit.setText("${document.data["namaPenerbit"]}")
                        tahunTerbit.setText("${document.data["tahunTerbit"]}")
                        jumlahHalaman.setText("${document.data["jumlahHalaman"]}")
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