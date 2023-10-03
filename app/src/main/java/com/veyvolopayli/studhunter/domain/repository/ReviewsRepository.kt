package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto

interface ReviewsRepository {
    suspend fun uploadReview(token: String, newReviewRequest: NewReviewRequest): ReviewDto
}