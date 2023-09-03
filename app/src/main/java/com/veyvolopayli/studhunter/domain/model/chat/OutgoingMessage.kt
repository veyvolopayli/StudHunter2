package com.veyvolopayli.studhunter.domain.model.chat

import kotlinx.serialization.Serializable

@Serializable
data class OutgoingMessage(
    val messageBody: String,
    val type: String
)
