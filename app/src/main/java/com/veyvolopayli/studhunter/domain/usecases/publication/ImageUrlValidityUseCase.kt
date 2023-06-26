package com.veyvolopayli.studhunter.domain.usecases.publication

import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class ImageUrlValidityUseCase @Inject constructor(
    private val repository: PublicationRepository
) {
    operator fun invoke(publicationId: String): Flow<Resource<List<String>>> = flow {
        val validImages = mutableListOf<String>()
        emit(Resource.Loading())
        repeat(10) { n ->
            try {
                repository.checkImageValidity(publicationId, n)
                validImages.add("${Constants.BASE_URL}image/$publicationId/image_$n")
            }
            catch (e: HttpException) {
                return@repeat
            }
            catch (e: Exception) {
                emit(Resource.Error("Some unexpected error"))
                return@flow
            }
        }
        emit(Resource.Success(validImages))
    }
}