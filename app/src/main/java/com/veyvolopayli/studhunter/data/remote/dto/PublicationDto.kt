package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.model.Publication
import okhttp3.internal.toLongOrDefault
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    val timestamp: Long,
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
        timestamp = timestamp.millsToDateTime() ?: "Ошибка",
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

private fun String.millsToDateTime(): String? {
    val mills = this.toLongOrNull() ?: run { return this }
    val date = Date(mills)
    val format = SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.millsToDateTime(): String? {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
    return format.format(date)
}