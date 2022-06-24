package id.ac.ukdw.a71190508_finaldatabuku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)


        val btnlihatdata = findViewById<Button>(R.id.bt_lihatdata)
        val btntambahdata = findViewById<Button>(R.id.bt_tambahdata)
        val btncaridata = findViewById<Button>(R.id.bt_caridata)
        val btnlogout = findViewById<Button>(R.id.bt_logout)

        btnlihatdata.setOnClickListener {
            val i = Intent(this, LihatDataBuku::class.java);
            startActivity(i)
        }

        btntambahdata.setOnClickListener {
            val i = Intent(this, TambahDataBuku::class.java);
            startActivity(i)
        }

        btncaridata.setOnClickListener {
            val i = Intent(this, CariDataBuku::class.java);
            startActivity(i)
        }

        btnlogout.setOnClickListener {
            googleClient.signOut().addOnSuccessListener{
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
      }
    }
}