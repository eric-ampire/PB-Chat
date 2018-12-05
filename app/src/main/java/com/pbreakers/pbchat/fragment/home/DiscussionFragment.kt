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
import com.pbreakers.pbchat.activity.DetailDialogActivity
import com.quickblox.chat.QBRestChatService
import com.quickblox.chat.model.QBChatDialog
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.core.request.QBRequestBuilder
import com.quickblox.core.request.QBRequestGetBuilder
import com.squareup.picasso.Picasso
import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore
import com.pbreakers.pbchat.model.FirebaseDialog
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_discussion.*
import kotlinx.android.synthetic.main.fragment_discussion.view.*
import java.text.DateFormat
import java.util.*


class DiscussionFragment : Fragment() {

    private val dialogAdapter = GroupAdapter<ViewHolder>()

    companion object {
        const val EXTRA_ID_DIALOG = "ID_DIALOG"
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discussion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogRefreshing.setOnRefreshListener { getMyDialog() }
        getMyDialog()
    }

    private fun getMyDialog() {

        dialogsList.adapter = dialogAdapter
        dialogRefreshing.isRefreshing = true

        val database = FirebaseFirestore.getInstance()
        val dialogCollection = database.collection("dialogs")
        dialogCollection.addSnapshotListener { querySnapshot, exception ->
            if (exception != null || querySnapshot == null) {
                dialogRefreshing.isRefreshing = false
                return@addSnapshotListener
            }

            dialogAdapter.clear()
            querySnapshot.asSequence().forEach {
                val dialog = it.toObject(FirebaseDialog::class.java)
                dialogAdapter.add(DialogItem(dialog))
            }

            dialogRefreshing.isRefreshing = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity

        appCompatActivity.supportActionBar?.title = "Discussion"
    }

    class DialogItem(val dialog: FirebaseDialog) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_dialog

        override fun bind(viewHolder: ViewHolder, position: Int) {
            val dateFormat = DateFormat.getDateInstance().format(Date(dialog.dateLastMessage))
            val textFormat = dateFormat.format("H:mm")
            val unReadMessage = dialog.messages.count { !it.isRedden }

            with(viewHolder.itemView) {
                findViewById<TextView>(R.id.dialogName).text = dialog.name
                findViewById<TextView>(R.id.dialogDate).text = textFormat
                findViewById<TextView>(R.id.dialogLastMessage).text = dialog.bodyLastMessage
                findViewById<TextView>(R.id.dialogUnreadBubble).text = "$unReadMessage"


                val avatar = findViewById<ImageView>(R.id.dialogAvatar)
                Picasso.get().load(dialog.photo ?: "empty").error(R.drawable.bg_user_placeholder).into(avatar)
            }
        }
    }
}
