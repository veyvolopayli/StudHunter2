package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OfferResponseDTO(
    val id: String,
    val chatID: String,
    val timestamp: Long,
    val accepted: Boolean,
    val requestID: String
)