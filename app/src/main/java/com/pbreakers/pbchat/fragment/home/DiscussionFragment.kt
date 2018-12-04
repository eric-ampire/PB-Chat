package com.pbreakers.pbchat.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar

import com.pbreakers.pbchat.R
import com.quickblox.chat.QBRestChatService
import com.quickblox.chat.model.QBChatDialog
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.core.request.QBRequestBuilder
import com.quickblox.core.request.QBRequestGetBuilder
import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_discussion.*
import kotlinx.android.synthetic.main.fragment_discussion.view.*
import java.util.*


class DiscussionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discussion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogRefreshing.isRefreshing = true
        dialogRefreshing.setOnRefreshListener {
            getAllDialogs(view)
        }
    }

    private fun getAllDialogs(view: View) {
        val requestBuilder = QBRequestGetBuilder().apply {
            limit = 100
        }

        QBRestChatService.getChatDialogs(null, requestBuilder).performAsync(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity

        appCompatActivity.supportActionBar?.title = "Discussion"
    }

    class DialogItem(val qbDialog: QBChatDialog) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_dialog

        override fun bind(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.itemView) {
                findViewById<TextView>(R.id.dialogName).text = qbDialog.name
                findViewById<TextView>(R.id.dialogDate).text = "Date"
                findViewById<TextView>(R.id.dialogLastMessage).text = qbDialog.lastMessage
                val avatar = findViewById<ImageView>(R.id.dialogAvatar)
                Picasso.get().load(qbDialog.photo).error(R.drawable.logo).into(avatar)
            }
        }
    }
}
