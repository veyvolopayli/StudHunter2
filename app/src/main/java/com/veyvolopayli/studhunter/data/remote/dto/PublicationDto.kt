package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.model.Publication

data class PublicationDto(
    val approved: Boolean,
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

fun PublicationDto.toPublication(): Publication {
    return Publication(
        id = id,
        title = title,
        description = description,
        price = price,
        priceType = priceType,
        timestamp = timestamp,
        imageUrl = imageUrl
    )
}

fun PublicationDto.toDetailedPublication(): DetailedPublication {
    return DetailedPublication(
        category = category,
        description = description,
        district = district,
        id = id,
        imageUrl = imageUrl,
        price = price,
        priceType = priceType,
        socials = socials,
        timestamp = timestamp,
        title = title,
        userId = userId
    )
}