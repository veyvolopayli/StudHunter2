package com.veyvolopayli.studhunter.presentation.home_screen

data class PublicationItem(
    val serviceTitle: String? = null,
    val serviceSubtitle: String? = null,
    val servicePrice: String? = null,
    val serviceSocials: String? = null,
    val serviceTime: String? = null,
    val serviceCategory: String? = null,
    val userId: String? = null,
    val serviceImageUrl: String? = null,
    var childId: String? = null,
    val servicePlace: String? = null,
    val approved: Int? = null
    )