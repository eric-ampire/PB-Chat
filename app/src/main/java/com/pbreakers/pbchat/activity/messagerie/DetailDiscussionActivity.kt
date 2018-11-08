package com.pbreakers.pbchat.activity.messagerie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R
import com.sendbird.android.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_detail_discussion.*
import kotlinx.android.synthetic.main.item_contact.view.*

class DetailDiscussionActivity : AppCompatActivity() {

    private var channel: GroupChannel? = null

    class MessageItem(val message: UserMessage) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                userName.text = message.message
                isOnLine.text = if (message.sender.userId == SendBird.getCurrentUser().userId) "Moi" else message.sender.nickname
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_discussion)

        val adapter = GroupAdapter<ViewHolder>()
        rvListMessage.adapter = adapter

        var userId: String
        var userProfile: String

        with(intent) {

            val userName = getStringExtra("userName")
            userProfile = getStringExtra("userProfile")
            userId = getStringExtra("userId")

            supportActionBar?.subtitle = userName
        }


        sendButton.setOnClickListener {
            val messageText = tapedMessage.text.toString()

            val ids = mutableListOf<String>(userId, SendBird.getCurrentUser().userId)
            tapedMessage.text.clear()


            val params = GroupChannelParams()
                .setName(userProfile)
                .setDistinct(true)
                .setPublic(false)
                .addUserIds(ids)

            GroupChannel.createChannel(params) { e, a ->
                if (a != null) {
                    toast(a.message)
                    return@createChannel
                }

                channel = e

                e.sendUserMessage(messageText) { a, error ->
                    if (error != null) {
                        toast(error.message)
                        return@sendUserMessage
                    }

                    val previewMessage = channel!!.createPreviousMessageListQuery()
                    previewMessage.load { e, err ->
                        if (err != null) {
                            toast(err.message)
                            return@load
                        }

                        e.forEach {
                            adapter.add(MessageItem(it as UserMessage))
                            adapter.notifyDataSetChanged()
                        }
                    }

                    toast("Message non lu " + e.unreadMessageCount)

                    toast("message send")
                }
            }
        }
    }
}
