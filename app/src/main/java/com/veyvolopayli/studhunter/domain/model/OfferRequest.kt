package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OfferRequest(
    val jobDeadline: Long
)