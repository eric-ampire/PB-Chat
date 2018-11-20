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
    }
}
