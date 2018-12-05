package com.pbreakers.pbchat.model

import android.text.BoringLayout
import com.quickblox.chat.query.QueryGetCountMessage

data class FirebaseUser(
    var uid: String = "",
    var displayName: String = "",
    var profile: String = ""
)

data class FirebaseMessage(
    var uid: String = "",
    var date: Long = 0,
    var body: String = "",
    var uidSender: String = "",
    var isRedden: Boolean = false
)

data class FirebaseDialog(
    var uid: String = "",
    var name: String = "",
    var bodyLastMessage: String = "",
    var dateLastMessage: Long = 0,
    var idsUser: List<String> = arrayListOf(),
    var messages: List<FirebaseMessage> = arrayListOf()
)