package com.pbreakers.pbchat.activity.messagerie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.pbreakers.pbchat.R
import com.sendbird.android.SendBird
import com.sendbird.android.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val adapter = GroupAdapter<ViewHolder>()
        rvListContact.adapter = adapter

        SendBird.createUserListQuery().next { mutableList, sendBirdException ->
            if (sendBirdException != null) return@next

            mutableList.forEach {
                adapter.add(ContactItem(it))
                adapter.notifyDataSetChanged()
            }
        }
    }

    class ContactItem(val user: User) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                userName.text = user.nickname
                isOnLine.text = if (user.isActive) "Online" else "Offline"

                Picasso.get()
                    .load(user.profileUrl)
                    .into(imageProfile)
            }
        }
    }
}
