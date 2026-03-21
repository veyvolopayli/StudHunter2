package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val description: String,
    val id: String,
    val imageUrl: String,
    val price: Int?,
    val priceType: String,
    val timestamp: String,
    val title: String
)