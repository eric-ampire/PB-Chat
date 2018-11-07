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

fun Context.toast(message: String?) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
}

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val adapter = GroupAdapter<ViewHolder>()
        rvListContact.adapter = adapter

        SendBird.createUserListQuery().next { mutableList, sendBirdException ->
            if (sendBirdException != null) {
                toast(sendBirdException.message)
                return@next
            }


            mutableList.forEach {
                toast(it.nickname)

                if (it.userId == SendBird.getCurrentUser().userId) return@forEach

                adapter.add(ContactItem(it))
                adapter.notifyDataSetChanged()
            }
        }

        adapter.setOnItemClickListener { item, view ->
            val user = (item as ContactItem).user
            val detailDiscussion = Intent(baseContext, DetailDiscussionActivity::class.java)

            detailDiscussion.putExtra("userName", user.nickname)
            detailDiscussion.putExtra("userProfile", user.profileUrl)
            detailDiscussion.putExtra("userId", user.userId)

            startActivity(detailDiscussion)
        }
    }

    class ContactItem(val user: User) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                userName.text = user.nickname
                isOnLine.text = if (user.isActive) "Online" else "Offline"

                Picasso.get()
                    .load(if (user.profileUrl.isEmpty()) "indefinie" else user.profileUrl)
                    .placeholder(R.color.gray)
                    .into(imageProfile)
            }
        }
    }
}
