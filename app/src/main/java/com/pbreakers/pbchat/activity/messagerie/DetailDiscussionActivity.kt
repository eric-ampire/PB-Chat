package com.pbreakers.pbchat.activity.messagerie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R
import com.sendbird.android.BaseChannel
import com.sendbird.android.GroupChannel
import com.sendbird.android.GroupChannelParams
import com.sendbird.android.SendBird
import kotlinx.android.synthetic.main.activity_detail_discussion.*

class DetailDiscussionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_discussion)

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

                e.sendUserMessage(messageText) { a, error ->
                    if (error != null) {
                        toast(error.message)
                        return@sendUserMessage
                    }


                    toast("message send")
                }
            }
        }
    }
}
