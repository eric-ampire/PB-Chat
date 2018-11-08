package com.pbreakers.pbchat.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.activity.messagerie.ContactActivity
import com.pbreakers.pbchat.activity.messagerie.DiscussionActivity
import com.pbreakers.pbchat.util.SendBirdConfig
import com.sendbird.android.*
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        SendBird.init(SendBirdConfig.applicationId, this)

        connect.setOnClickListener {
            SendBird.connect("user2") { user, exception ->
                if (exception != null) {
                    Toast.makeText(baseContext, exception.message, Toast.LENGTH_LONG).show()
                    Log.e("ericampire", exception.message)

                    return@connect
                }

                startActivity(Intent(this@SplashActivity, DiscussionActivity::class.java))
            }
        }

        disconnect.setOnClickListener {
            SendBird.disconnect {
                Toast.makeText(baseContext, "Vous est deconnecter", Toast.LENGTH_LONG).show()
            }
        }
    }
}
