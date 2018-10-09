package com.pbreakers.pbchat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.quickblox.auth.QBAuth
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

        login("ericampire", "ericampire")
    }

    private fun login(username: String, password: String) {
        QBAuth.createSession()
        val user = QBUser(username, password)
        QBUsers.signIn(user, object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser, bundle: Bundle) {
                Toast.makeText(baseContext, "Sign in", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QBResponseException) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
