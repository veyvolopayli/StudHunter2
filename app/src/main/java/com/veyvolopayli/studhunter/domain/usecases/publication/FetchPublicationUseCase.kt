package com.veyvolopayli.studhunter.domain.usecases.publication

import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class FetchPublicationUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository
) {
    operator fun invoke(id: String): Flow<Resource<Response<PublicationDto>>> = flow {
        try {
            emit(Resource.Loading())
            val response = publicationRepository.fetchPublication(id)
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Http error"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Unexpected error"))
        }
    }
}