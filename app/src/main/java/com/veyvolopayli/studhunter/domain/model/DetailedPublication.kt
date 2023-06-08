package com.veyvolopayli.studhunter.domain.model

data class DetailedPublication(
    val category: String,
    val description: String,
    val district: String,
    val id: String,
    val imageUrl: String,
    val price: Int,
    val priceType: Int,
    val socials: String,
    val timestamp: String,
    val title: String,
    val userId: String
)
