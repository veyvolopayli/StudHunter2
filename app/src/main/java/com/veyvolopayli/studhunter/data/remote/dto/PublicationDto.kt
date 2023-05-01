package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.Publication

data class PublicationDto(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: Int,
    val priceType: Int,
    val district: String?,
    val timestamp: String,
    val category: String,
    val userId: String,
    val socials: String,
    val approved: Boolean?
)

fun PublicationDto.toPublication(): Publication {
    return Publication(
        id = id,
        imageUrl = imageUrl,
        title = title,
        description = description,
        price = price,
        priceType = priceType,
        timestamp = timestamp
    )
}