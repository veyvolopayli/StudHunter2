package com.veyvolopayli.studhunter.presentation.publication_screen

data class PublicationScreenState(
    val description: String = "",
    val publicationID: String = "",
    val price: Int? = 0,
    val priceType: String = "",
    val date: String = "",
    val title: String = "",
    val userId: String = "",
    val username: String = "",
    val userFullName: String = "",
    val userRating: Double = 5.0,
    val isUserOwner: Boolean = false,
    val isLoading: Boolean = true
)