package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto
import com.veyvolopayli.studhunter.domain.repository.ReviewsRepository
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val api: StudHunterApi
) : ReviewsRepository {
    override suspend fun uploadReview(
        token: String,
        newReviewRequest: NewReviewRequest
    ): ReviewDto {
        return api.uploadReview(token, newReviewRequest)
    }
}