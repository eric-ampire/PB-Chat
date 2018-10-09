package com.pbreakers.pbchat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbreakers.pbchat.R
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
        registerSession()

        val user = QBUser("ericampire", "ericampire")

        QBUsers.signUp(user, object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser, bundle: Bundle) {
                Toast.makeText(baseContext, "Succes", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QBResponseException) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun registerSession() {
        QBAuth.createSession(object : QBEntityCallback<QBSession> {
            override fun onSuccess(p0: QBSession?, p1: Bundle?) {
                Toast.makeText(baseContext, "Succes session", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QBResponseException) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun qbChatInitialisation() {
        QBSettings.getInstance().init(this, applicationId, authorizationKey, authorizationSecret)
        QBSettings.getInstance().accountKey = accoundKey
    }
}
