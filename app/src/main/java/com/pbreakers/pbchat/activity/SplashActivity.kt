package com.pbreakers.pbchat.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R
import com.quickblox.auth.session.QBSessionManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (QBSessionManager.getInstance().sessionParameters == null) {
            startActivity(Intent(baseContext, AuthActivity::class.java))
            finish()
        } else {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }
}
