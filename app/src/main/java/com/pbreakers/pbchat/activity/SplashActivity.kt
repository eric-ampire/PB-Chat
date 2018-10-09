package com.pbreakers.pbchat.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.quickblox.auth.QBAuth
import com.quickblox.auth.model.QBSession
import com.quickblox.chat.QBChatService
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.model.QBUser

class SplashActivity : AppCompatActivity(), QBEntityCallback<QBSession> {
    override fun onSuccess(p0: QBSession?, p1: Bundle?) {
        Toast.makeText(baseContext, "Okay", Toast.LENGTH_LONG).show()
    }

    override fun onError(p0: QBResponseException?) {
        Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val qbChatService = QBChatService.getInstance()
        qbChatService.

        val user = QBUser("ericampire", "ericampire")

        QBAuth.createSession(user, this)
    }
}
