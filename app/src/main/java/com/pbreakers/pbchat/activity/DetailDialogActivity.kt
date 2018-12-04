package com.pbreakers.pbchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.fragment.home.DiscussionFragment.Companion.EXTRA_ID_DIALOG
import com.pbreakers.pbchat.model.PBMessage
import com.pbreakers.pbchat.model.toIMessage
import com.quickblox.auth.session.QBSessionManager
import com.quickblox.core.exception.QBResponseException
import com.quickblox.chat.model.QBChatMessage
import com.quickblox.core.QBEntityCallback
import com.quickblox.chat.QBRestChatService
import com.quickblox.chat.exception.QBChatException
import com.quickblox.chat.listeners.QBChatDialogMessageListener
import com.quickblox.chat.request.QBMessageGetBuilder
import com.quickblox.chat.model.QBChatDialog
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_detail_dialog.*


class DetailDialogActivity : AppCompatActivity(), ImageLoader, QBChatDialogMessageListener {

    private val sessionParameters = QBSessionManager.getInstance().sessionParameters
    private val messagesListAdapter = MessagesListAdapter<IMessage>(sessionParameters.userId.toString(), this)
    private val chatDialog = QBChatDialog()

    override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dialog)

        dialogConfiguration()

        input.setInputListener {
            Log.e("EOOR", "${sessionParameters.userId} - ${chatDialog.dialogId}")
            val qbChatMessage = QBChatMessage().apply {
                this.body = it.toString()
                this.dialogId = chatDialog.dialogId
                this.senderId = sessionParameters.userId
            }

            chatDialog.sendMessage(qbChatMessage)
            true
        }


        messagesList.setAdapter(messagesListAdapter)
        listMessageRefreshing.setOnRefreshListener {

        }
    }

    private fun dialogConfiguration() {
        val dialogId = intent.getStringExtra(EXTRA_ID_DIALOG)
        chatDialog.dialogId = dialogId
        chatDialog.addMessageListener(this)
    }

    override fun processMessage(body: String, chatMessage: QBChatMessage, p2: Int?) {
        messagesListAdapter.addToStart(chatMessage.toIMessage(), true)
    }

    override fun processError(p0: String?, p1: QBChatException?, p2: QBChatMessage?, p3: Int?) {
    }

    fun f() {

        val messageGetBuilder = QBMessageGetBuilder()
        messageGetBuilder.limit = 500

        chatDialog.addMessageListener(this)

        val performer = QBRestChatService.getDialogMessages(chatDialog, messageGetBuilder)
        performer.performAsync(object : QBEntityCallback<ArrayList<QBChatMessage>> {
            override fun onSuccess(qbChatMessages: ArrayList<QBChatMessage>, bundle: Bundle) {
                qbChatMessages.asSequence().forEach {
                    messagesListAdapter.addToStart(it.toIMessage(), true)
                }
            }

            override fun onError(e: QBResponseException?) {
                Toast.makeText(baseContext, e?.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
