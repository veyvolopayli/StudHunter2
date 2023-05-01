package com.veyvolopayli.studhunter.domain.model

data class Publication(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: Int,
    val priceType: Int,
    val timestamp: String,
)
