package id.ac.ukdw.a71190508_finaldatabuku

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




class BukuAdapter (val listBuku: ArrayList<Buku>): RecyclerView.Adapter<BukuAdapter.PendudukHolder>(){
    class PendudukHolder(val v: View): RecyclerView.ViewHolder(v){
        var buku: Buku? = null
        val firestore = FirebaseFirestore.getInstance()

        fun bindView(buku: Buku){
            this.buku = buku
            v.findViewById<TextView>(R.id.tv_judul).text = buku.judulBuku
            v.findViewById<TextView>(R.id.tv_namaPenulis).text = buku.namaPenulis
            v.findViewById<TextView>(R.id.tv_penerbit).text = buku.namaPenerbit
            v.findViewById<TextView>(R.id.tv_thnTerbit).text = buku.tahunTerbit
            v.findViewById<TextView>(R.id.tv_halBuku).text = buku.jumlahHalaman

            //button delete
            v.findViewById<Button>(R.id.bt_delete).setOnClickListener{

                firestore.collection("Buku")
                    .whereEqualTo("judulBuku", buku.judulBuku)
                    .get()
                    .addOnSuccessListener {
                        for (document in it){
                            firestore.collection("Buku").document(document.id).delete()
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

                val i = Intent(v.context, EditDataBuku::class.java)
                i.putExtra("judulBuku",buku.judulBuku)
                i.putExtra("namaPenulis",buku.namaPenulis)
                i.putExtra("namaPenerbit",buku.namaPenerbit)
                i.putExtra("tahunTerbit",buku.tahunTerbit)
                i.putExtra("jumlahHalaman",buku.jumlahHalaman)
                v.context.startActivity(i)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuAdapter.PendudukHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_buku, parent, false)
        return PendudukHolder(v)
    }
    //memilih file layout XML yang akan dijadikan container

    override fun onBindViewHolder(holder: BukuAdapter.PendudukHolder, position: Int) {
        //memasang data ke dalam file layout XML yang telah dipilih
        holder.bindView(listBuku[position])
    }

    override fun getItemCount(): Int {
        //mengembalikan jumlah item yang terdapat pada RecyclerView
        return listBuku.size
    }

}