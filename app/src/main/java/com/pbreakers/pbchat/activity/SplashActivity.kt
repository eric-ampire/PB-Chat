package com.pbreakers.pbchat.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.util.QBChatConfig
import com.pbreakers.pbchat.util.QBChatConfig.accoundKey
import com.pbreakers.pbchat.util.QBChatConfig.applicationId
import com.pbreakers.pbchat.util.QBChatConfig.authorizationKey
import com.pbreakers.pbchat.util.QBChatConfig.authorizationSecret
import com.quickblox.auth.QBAuth
import com.quickblox.auth.model.QBSession
import com.quickblox.chat.QBChatService
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.QBSettings
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        qbChatInitialisation()
        startActivity(Intent(baseContext, LoginActivity::class.java))

    }

    private fun signUp(username: String, password: String) {
        val user = QBUser(username, password)
        QBUsers.signUp(user, object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser, bundle: Bundle) {
                Toast.makeText(baseContext, "Succes", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QBResponseException) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun qbChatInitialisation() {
        QBSettings.getInstance().apply {
            init(baseContext, QBChatConfig.applicationId, authorizationKey, authorizationSecret)
            accountKey = accoundKey
        }
    }
}
