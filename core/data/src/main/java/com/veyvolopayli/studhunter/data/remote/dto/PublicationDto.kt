package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.Publication
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Serializable
data class PublicationDto(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: Int?,
    val priceType: String,
    val district: String?,
    val timestamp: Long,
    val category: String,
    val userId: String,
    val socials: String,
    val approved: Boolean?
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

private fun String.millsToDateTime(): String? {
    val mills = this.toLongOrNull() ?: run { return this }
    val date = Date(mills)
    val format = SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.millsToDateTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
    return format.format(date)
}