package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.common.millsToTime
import com.veyvolopayli.studhunter.domain.model.Message
import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MessageDTO(
    val id: String,
    val fromId: String,
    val timestamp: Long,
    val messageBody: String,
    val chatId: String,
    val type: String
) {
    fun toMessage(): Message {
        return Message(
            messageBody = this.messageBody,
            type = this.type,
            timestamp = this.timestamp.millsToTime(),
            userID = this.fromId
        )
    }

}