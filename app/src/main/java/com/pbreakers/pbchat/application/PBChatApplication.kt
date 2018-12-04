package com.pbreakers.pbchat.application

import android.app.Application
import com.quickblox.auth.session.QBSettings
import com.quickblox.chat.QBChatService
import com.quickblox.core.QBHttpConnectionConfig
import com.quickblox.core.StoringMechanism

class PBChatApplication : Application() {

    companion object {
        const val ACCOUNT_KEY = "4sZ3iiAwSXd2vpvGvz4-"
        const val APP_ID = "74066"
        const val AUTHORIZATION_KEY = "buGva2DAcGLDmjK"
        const val AUTHORIZATION_SECRET = "3GUMWaZmK3qKp7L"
    }

    override fun onCreate() {
        super.onCreate()


        QBSettings.getInstance().storingMehanism = StoringMechanism.UNSECURED
        QBSettings.getInstance().init(applicationContext, APP_ID, AUTHORIZATION_KEY, AUTHORIZATION_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY
        QBHttpConnectionConfig.setConnectTimeout(9000)

        QBChatService.setDefaultPacketReplyTimeout(9000)

        val chatServiceConfigurationBuilder = QBChatService.ConfigurationBuilder()
        chatServiceConfigurationBuilder.socketTimeout = 60 //Sets chat socket's read timeout in seconds
        chatServiceConfigurationBuilder.isKeepAlive = true //Sets connection socket's keepAlive option.
        chatServiceConfigurationBuilder.isUseTls = true //Sets the TLS security mode used when making the connection. By default TLS is disabled.
        QBChatService.setConfigurationBuilder(chatServiceConfigurationBuilder)

    }
}