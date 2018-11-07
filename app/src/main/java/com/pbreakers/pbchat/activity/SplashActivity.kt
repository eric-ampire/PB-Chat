package com.pbreakers.pbchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.util.SendBirdConfig
import com.sendbird.android.SendBird
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar.visibility = View.VISIBLE
        SendBird.init(SendBirdConfig.applicationId, this)
        SendBird.connect("user2") { user, exception ->
            if (exception != null) {
                Toast.makeText(baseContext, exception.message, Toast.LENGTH_LONG).show()
                Log.e("ericampire", exception.message)

                progressBar.visibility = View.GONE
                return@connect
            }

            progressBar.visibility = View.GONE
            Toast.makeText(baseContext, "Vous est connecter", Toast.LENGTH_LONG).show()
            Log.e("ericampire", user.nickname)
        }
    }
}
