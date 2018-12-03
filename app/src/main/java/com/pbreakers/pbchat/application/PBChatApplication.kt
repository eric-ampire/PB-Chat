package com.pbreakers.pbchat.application

import android.app.Application
import com.quickblox.auth.session.QBSettings

class PBChatApplication : Application() {

    companion object {
        const val ACCOUNT_KEY = "4sZ3iiAwSXd2vpvGvz4-"
        const val APP_ID = "74066"
        const val AUTHORIZATION_KEY = "buGva2DAcGLDmjK"
        const val AUTHORIZATION_SECRET = "3GUMWaZmK3qKp7L"
    }

    override fun onCreate() {
        super.onCreate()

        QBSettings.getInstance().init(applicationContext, APP_ID, AUTHORIZATION_KEY, AUTHORIZATION_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY
    }
}