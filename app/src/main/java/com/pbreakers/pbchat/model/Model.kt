package com.pbreakers.pbchat.model

import com.quickblox.chat.model.QBChatDialog
import com.quickblox.chat.model.QBChatMessage
import com.quickblox.users.model.QBUser
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

class PBUser(val qbUser: QBUser) : IUser {
    override fun getAvatar(): String = "df"
    override fun getName(): String = qbUser.fullName
    override fun getId(): String = qbUser.id.toString()
}

class PBMessage(val qbMessage: QBChatMessage) : IMessage {
    override fun getId(): String = qbMessage.id
    override fun getCreatedAt(): Date = Date(qbMessage.dateSent)
    override fun getUser(): IUser {
        TODO()
    }


    override fun getText(): String = qbMessage.body
}

class PBDialog(val qbChatDialog: QBChatDialog) : IDialog<IMessage> {
    override fun getDialogPhoto(): String = qbChatDialog.photo
    override fun getUnreadCount(): Int = qbChatDialog.unreadMessageCount
    override fun setLastMessage(message: IMessage) { }

    override fun getId(): String = qbChatDialog.id.toString()
    override fun getUsers(): MutableList<out IUser> {
        TODO()
    }

    override fun getLastMessage(): IMessage {
        TODO()
    }

    override fun getDialogName(): String = qbChatDialog.name

}