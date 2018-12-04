package com.pbreakers.pbchat.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.pbreakers.pbchat.R
import com.quickblox.chat.QBRestChatService
import com.quickblox.chat.model.QBChatDialog
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.core.request.QBRequestBuilder
import com.quickblox.core.request.QBRequestGetBuilder
import java.util.ArrayList


class DiscussionFragment : Fragment(), QBEntityCallback<ArrayList<QBChatDialog>> {
    override fun onSuccess(dialogs: ArrayList<QBChatDialog>, bundle: Bundle) {
        Toast.makeText(activity, dialogs.first().name, Toast.LENGTH_LONG).show()
    }

    override fun onError(error: QBResponseException) {
        Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discussion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}
