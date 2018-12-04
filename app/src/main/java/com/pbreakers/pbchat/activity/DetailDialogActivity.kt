package com.pbreakers.pbchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R
import com.quickblox.core.exception.QBResponseException
import com.quickblox.chat.model.QBChatMessage
import com.quickblox.core.QBEntityCallback
import com.quickblox.chat.QBRestChatService
import com.quickblox.chat.request.QBMessageGetBuilder
import com.quickblox.chat.model.QBChatDialog



class DetailDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dialog)
    }

    fun f() {
        val chatDialog = QBChatDialog("53cfc593efa3573ebd000017")

        val messageGetBuilder = QBMessageGetBuilder()
        messageGetBuilder.limit = 500

        val performAsync = QBRestChatService.getDialogMessages(chatDialog, messageGetBuilder)
        performAsync.performAsync(object : QBEntityCallback<ArrayList<QBChatMessage>> {
            override fun onSuccess(qbChatMessages: ArrayList<QBChatMessage>, bundle: Bundle) {

            }

            override fun onError(e: QBResponseException) {

            }
        })
    }
}
