package com.veyvolopayli.studhunter.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OutgoingMessageDTO(
    val messageBody: String,
    val type: String
)
