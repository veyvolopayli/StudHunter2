package com.veyvolopayli.studhunter.domain.model

data class MyPublication(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: Int?,
    val priceType: String,
    val timestamp: Long,
    val userId: String,
    val approved: Boolean?,
    val views: Long,
    val favorites: Long,
)
