package com.pbreakers.pbchat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.quickblox.auth.QBAuth
import com.quickblox.auth.model.QBSession
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import java.net.PasswordAuthentication
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn("ericampire", "ericampire")
    }

    private fun signIn(username: String, password: String) {
        val user = QBUser(username, password)

        QBAuth.createSession(user, object : QBEntityCallback<QBSession> {
            override fun onSuccess(user: QBSession, bundle: Bundle) {
                Toast.makeText(baseContext, "Sign in", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QBResponseException) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
