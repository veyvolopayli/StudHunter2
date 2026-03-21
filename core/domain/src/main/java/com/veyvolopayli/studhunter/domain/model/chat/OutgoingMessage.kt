package com.veyvolopayli.studhunter.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("OutgoingMessage")
data class OutgoingMessage(
    val messageBody: String,
    val messageType: String
) : DataTransfer