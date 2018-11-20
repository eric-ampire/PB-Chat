package com.pbreakers.pbchat.activity.messagerie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.pbreakers.pbchat.R
import android.content.Intent
import com.sendbird.android.SendBird
import com.sendbird.android.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.item_contact.view.*
import java.time.format.DateTimeFormatter

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
    }

    class ContactItem(val user: User) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                userName.text = user.nickname
                isOnLine.text = if (user.connectionStatus == User.ConnectionStatus.ONLINE) "Online" else "Offline"

                if (user.profileUrl.isNotEmpty()) {
                    Picasso.get().load(user.profileUrl).placeholder(R.color.gray).into(imageProfile)
                }
            }
        }
    }
}
