package com.veyvolopayli.studhunter.domain.model.review

data class ReviewDto(
    val id: String,
    val reviewerId: String,
    val userId: String,
    val review: Double,
    val reviewMessage: String,
    val timestamp: Long,
    val publicationId: String
)