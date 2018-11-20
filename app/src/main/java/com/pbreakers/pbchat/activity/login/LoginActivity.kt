package com.pbreakers.pbchat.activity.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.messagerie.DiscussionActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connectWithGoogleButton.setOnClickListener {
            startActivity(Intent(baseContext, DiscussionActivity::class.java))
        }
    }
}
