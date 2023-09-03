package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OfferResponse(
    val accepted: Boolean
)