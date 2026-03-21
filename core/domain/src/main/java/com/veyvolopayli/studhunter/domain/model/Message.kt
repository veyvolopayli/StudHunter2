package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val messageBody: String,
    val messageType: String,
    val timestamp: String,
    val userID: String
)