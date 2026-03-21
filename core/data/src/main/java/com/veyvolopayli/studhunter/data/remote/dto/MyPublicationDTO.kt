package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.MyPublication

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

fun MyPublicationDTO.toMyPublication(): MyPublication =
    MyPublication(
        id = id,
        imageUrl = imageUrl,
        title = title,
        description = description,
        price = price,
        priceType = priceType,
        timestamp = timestamp,
        userId = userId,
        approved = approved,
        views = views,
        favorites = favorites,
    )
