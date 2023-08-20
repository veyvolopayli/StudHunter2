package com.veyvolopayli.studhunter.domain.model

import io.ktor.util.date.*
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Message(
    val messageBody: String,
    val type: String,
    val timestamp: String,
    val userID: String
)