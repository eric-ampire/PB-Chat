package com.pbreakers.pbchat.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(baseContext, AuthActivity::class.java))
        finish()
    }
}
