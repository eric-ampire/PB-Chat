package com.pbreakers.pbchat.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.messagerie.DiscussionActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : Thread() {
            override fun run() {
                Thread.sleep(1000)

                if (FirebaseAuth.getInstance().currentUser == null) {
                    startActivity(Intent(baseContext, LoginActivity::class.java))
                    finish()

                } else {
                    startActivity(Intent(baseContext, DiscussionActivity::class.java))
                    finish()
                }
            }
        }.start()
    }
}
