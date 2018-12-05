package com.pbreakers.pbchat.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.pbreakers.pbchat.R
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_detail_dialog.*


class DetailDialogActivity : AppCompatActivity(), ImageLoader {



    override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dialog)


        listMessageRefreshing.setOnRefreshListener { }
    }
}
