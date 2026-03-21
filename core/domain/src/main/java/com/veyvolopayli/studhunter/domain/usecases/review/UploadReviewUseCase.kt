package com.veyvolopayli.studhunter.domain.usecases.review

import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import com.veyvolopayli.studhunter.domain.repository.ReviewsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class UploadReviewUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository,
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke(taskId: String, reviewValue: Int, reviewMessage: String) = flow<Resource<ReviewDto>> {
        try {
            val token = prefsRepository.getJwtToken() ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val newReviewRequest = NewReviewRequest(
                taskId = taskId, reviewValue = reviewValue.toDouble(), reviewMessage = reviewMessage
            )
            val review = reviewsRepository.uploadReview(token, newReviewRequest)
            emit(Resource.Success(review))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(ErrorType.NetworkError()))
            e.printStackTrace()
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> emit(Resource.Error(ErrorType.Unauthorized()))
                409 -> emit(Resource.Error(ErrorType.ServerError()))
                400 -> emit(Resource.Error(ErrorType.ServerError()))
            }
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError()))
            e.printStackTrace()
        }
    }
}