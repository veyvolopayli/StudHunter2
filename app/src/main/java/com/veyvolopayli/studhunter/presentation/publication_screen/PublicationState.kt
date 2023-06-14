package com.veyvolopayli.studhunter.presentation.publication_screen

data class PublicationState(
    val category: String = "",
    val description: String = "",
    val district: String = "",
    val id: String = "",
    val price: Int? = 0,
    val priceType: String,
    val socials: String = "",
    val timestamp: String = "",
    val title: String = "",
    val userId: String = ""
)
