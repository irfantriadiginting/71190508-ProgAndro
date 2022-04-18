package id.ac.ukdw.pertemuan7_71190508


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //siapkan data dummy
        val listContact = ArrayList<Contact>()
        listContact.add(Contact("Joko Widodo", "0-24-351-5542", R.mipmap.jokowi,"Kelurahan Sumber, Kecamatan Banjarsari, Solo.","joko.widodo@ht.ugm.ac.id"))
        listContact.add(Contact("Vladimir Putin", "+7(3952)91-84-74", R.mipmap.putin,"Gelendzhik Urban Okrug, Krasnodar Krai, Russia","vladimir.putin@jd.gov.ru"))
        listContact.add(Contact("Joe Biden", "773-545-6846",  R.mipmap.biden,"Greenville, Delaware, Wilmington","joe.biden@ec.gov.us"))

        //siapkan RecyclerView
        val rvContact = findViewById<RecyclerView>(R.id.rvListContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(listContact)
        rvContact.adapter = adapter


    }
}