package com.pbreakers.pbchat.activity.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.messagerie.DiscussionActivity
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connectWithGoogleButton.setSize(SignInButton.SIZE_STANDARD)
        connectWithGoogleButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            val client = GoogleSignIn.getClient(this, gso)
            val googleIntent = client.signInIntent

            startActivityForResult(googleIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            task.addOnSuccessListener {
                progressBar.visibility = View.GONE
                startActivity(Intent(baseContext, DiscussionActivity::class.java))
                finish()

            }.addOnFailureListener {
                progressBar.visibility = View.GONE
                Snackbar.make(connectWithGoogleButton, it.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
