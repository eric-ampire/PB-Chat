package com.pbreakers.pbchat.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.sendbird.android.SendBird

class PBChatApplication : Application() {

    private val applicationId = "705EB71A-6D70-48D9-AD21-DA581C74547D"
    private val apiToken = "7ba342d0838e7ea066717f0db8ffa809f363d21d"
    private val apiUrl = "https://api.sendbird.com"

    override fun onCreate() {
        super.onCreate()
        SendBird.init(applicationId, applicationContext)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}