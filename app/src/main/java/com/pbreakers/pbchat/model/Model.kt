package com.pbreakers.pbchat.model

import com.quickblox.chat.model.QBChatDialog
import com.quickblox.chat.model.QBChatMessage
import com.quickblox.users.QBUsers
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
        val qbUser = QBUsers.getUser(qbMessage.senderId).perform()
        return qbUser.toIUser()
    }

    override fun getText(): String = qbMessage.body
}

fun QBUser.toIUser(): IUser {
    return PBUser(this)
}

fun QBChatMessage.toIMessage(): IMessage {
    return PBMessage(this)
}

class PBDialog(val qbChatDialog: QBChatDialog) : IDialog<IMessage> {
    override fun getDialogPhoto(): String = qbChatDialog.photo
    override fun getUnreadCount(): Int = qbChatDialog.unreadMessageCount
    override fun setLastMessage(message: IMessage) { }

    override fun getId(): String = qbChatDialog.id.toString()
    override fun getUsers(): MutableList<out IUser> {
        val users = arrayListOf<IUser>()
        qbChatDialog.occupants.asSequence().forEach {
            val user = QBUsers.getUser(it).perform()
            users += user.toIUser()
        }

        return users
    }

    override fun getLastMessage(): IMessage {

        return object : IMessage {
            override fun getId(): String = UUID.randomUUID().toString()
            override fun getCreatedAt(): Date = Date(qbChatDialog.lastMessageDateSent)

            override fun getUser(): IUser {
                val user = QBUsers.getUser(qbChatDialog.lastMessageUserId).perform()
                return user.toIUser()
            }

            override fun getText(): String = qbChatDialog.lastMessage
        }
    }

    override fun getDialogName(): String = qbChatDialog.name

}