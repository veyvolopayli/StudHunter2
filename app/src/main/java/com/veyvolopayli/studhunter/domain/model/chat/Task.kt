package com.veyvolopayli.studhunter.domain.model.chat

import io.ktor.util.date.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Task")
data class Task(
    val id: String,
    val executorId: String,
    val customerId: String,
    val publicationId: String,
    val chatId: String,
    val status: String = "",
    val timestamp: Long,
    val deadline: Long
): DataTransfer