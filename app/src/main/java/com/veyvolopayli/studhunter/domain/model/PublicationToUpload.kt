package com.veyvolopayli.studhunter.domain.model

data class PublicationToUpload(
    val category: String,
    val description: String,
    val district: String,
    val price: Int?,
    val priceType: Int,
    val socials: String,
    val title: String,
    val userId: String
)