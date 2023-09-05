package com.veyvolopayli.studhunter.domain.model

import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DealRequest")
data class OfferRequestDTO(
    val id: String,
    val chatID: String,
    val timestamp: Long,
    val expiredIn: Long,
    val jobDeadline: Long
) : DataTransfer