package com.pbreakers.pbchat.activity.messagerie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbreakers.pbchat.R

class DetailDiscussionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_discussion)

        with(intent) {

            val userName = getStringExtra("userName")
            val userProfile = getStringExtra("userProfile")
            val userId = getStringExtra("userId")

            supportActionBar?.subtitle = userName
        }
    }
}
