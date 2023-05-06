package com.veyvolopayli.studhunter.domain.model

data class Publication(
    val description: String,
    val id: String,
    val imageUrl: String,
    val price: Int,
    val priceType: Int,
    val timestamp: String,
    val title: String
)