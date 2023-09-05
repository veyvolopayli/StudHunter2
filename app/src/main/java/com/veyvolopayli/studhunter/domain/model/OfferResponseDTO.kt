package com.veyvolopayli.studhunter.domain.model

import com.google.gson.annotations.SerializedName
import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DealResponse")
data class OfferResponseDTO(
    val id: String,
    val chatID: String,
    val timestamp: Long,
    val accepted: Boolean,
    val requestID: String
) : DataTransfer