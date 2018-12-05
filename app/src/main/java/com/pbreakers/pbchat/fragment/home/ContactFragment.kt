package com.pbreakers.pbchat.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

import com.pbreakers.pbchat.R
import com.pbreakers.pbchat.model.FirebaseChatUser
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.core.request.QBPagedRequestBuilder
import com.quickblox.core.request.QBRequestGetBuilder
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_contact.view.*
import kotlinx.android.synthetic.main.item_contact.view.*


class ContactFragment : Fragment() {

    private val contactAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllContact()
        contactRefreshing.setOnRefreshListener { getAllContact() }
    }

    private fun getAllContact() {
        val database = FirebaseFirestore.getInstance()
        val userCollection = database.collection("users")
        userCollection.addSnapshotListener { querySnapshot, exception ->
            if (exception != null || querySnapshot == null) {
                contactRefreshing.isRefreshing = false
                return@addSnapshotListener
            }

            querySnapshot.asSequence().forEach {
                val user = it.toObject(FirebaseChatUser::class.java)
                contactAdapter.add(UserItem(user))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity

        appCompatActivity.supportActionBar?.title = "Contact"
    }

    class UserItem(val user: FirebaseChatUser) : Item<ViewHolder>() {
        override fun getLayout(): Int = R.layout.item_contact

        override fun bind(viewHolder: ViewHolder, position: Int) {
           with(viewHolder.itemView) {
               userDisplayName.text = user.displayName

               val profileUrl = user.profile ?: "Vide"
               Picasso.get().load(profileUrl).error(R.drawable.bg_user_placeholder)
           }
        }
    }
}
