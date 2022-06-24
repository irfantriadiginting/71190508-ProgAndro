package id.ac.ukdw.a71190508_finaldatabuku


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var loginView: TextView
    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginView = findViewById<Button>(R.id.bt_loginEmail)
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        client = GoogleSignIn.getClient(this, options)
        loginView.setOnClickListener {
            val intent = client.signInIntent
            startActivityForResult(intent, 1001)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1001){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){
                        val i = Intent(this, SecondActivity::class.java)
                        startActivity(i)
                        finish()
                    }else{
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser!=null){
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
            finish()
        }
    }}

