package com.pbreakers.pbchat.util

import android.os.Bundle
import android.util.Log
import com.quickblox.auth.QBAuth
import com.quickblox.auth.session.QBSession
import com.quickblox.auth.session.QBSessionManager
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser

object QBUtil {

    fun signIn(username: String, password: String, next: () -> Unit) {
        val user = QBUser(username, password)
        QBAuth.createSession(user).performAsync(object : QBEntityCallback<QBSession> {
            override fun onSuccess(session: QBSession, bundle: Bundle) {
                next()
            }

            override fun onError(error: QBResponseException) {
                Log.e("QBUtil", error.message)
            }
        })
    }
}