package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OfferRequestDTO(
    val id: String,
    val chatID: String,
    val timestamp: Long,
    val expiredIn: Long,
    val jobDeadline: Long
)