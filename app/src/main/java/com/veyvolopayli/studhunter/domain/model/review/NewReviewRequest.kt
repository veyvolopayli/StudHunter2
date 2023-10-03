package com.veyvolopayli.studhunter.domain.model.review

import kotlinx.serialization.Serializable

data class NewReviewRequest(
    val taskId: String,
    val reviewValue: Double,
    val reviewMessage: String = ""
)