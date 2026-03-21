package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.common.millsToTime
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Message")
data class MessageDTO(
    val id: String,
    val fromId: String,
    val timestamp: Long,
    val messageBody: String,
    val chatId: String,
    val messageType: String
): DataTransfer {
    fun toMessage(): Message {
        return Message(
            messageBody = this.messageBody,
            messageType = this.messageType,
            timestamp = this.timestamp.millsToTime(),
            userID = this.fromId
        )
    }

}