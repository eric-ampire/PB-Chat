package com.pbreakers.pbchat.activity.messagerie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R
import com.sendbird.android.GroupChannel
import com.sendbird.android.GroupChannelListQuery
import com.sendbird.android.SendBird
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_discussion.*
import kotlinx.android.synthetic.main.item_contact.view.*

class DiscussionActivity : AppCompatActivity() {

    class DiscussionItem(val groupChannel: GroupChannel) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                userName.text = groupChannel.name
                isOnLine.text = "${groupChannel.lastMessage} ${groupChannel.lastMessage.createdAt}"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussion)
        supportActionBar?.title = "Discussion 😃"

        contactButton.setOnClickListener {
            startActivity(Intent(baseContext, ContactActivity::class.java))
        }

        val adapter = GroupAdapter<ViewHolder>()
        rvListDiscussion.adapter = adapter

        val query  = GroupChannel.createMyGroupChannelListQuery()
        query.next { mutableList, sendBirdException ->
            if (sendBirdException != null) {
                toast(sendBirdException.message)
                return@next
            }

            mutableList.forEach {
                adapter.add(DiscussionItem(it))
                adapter.notifyDataSetChanged()
            }
        }
    }
}