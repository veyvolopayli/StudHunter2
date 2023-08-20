package com.veyvolopayli.studhunter.data.remote.dto

data class MyPublicationDTO(
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
    val favorites: Long
)
