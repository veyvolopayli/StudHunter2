package com.veyvolopayli.studhunter.domain.usecases.publication

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class FetchPublicationUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(id: String): Flow<Resource<Response<PublicationDto>>> = flow {
        try {
            emit(Resource.Loading())
            val token = prefs.getString("jwt", null) ?: run {
                emit(Resource.Error("Unauthorized"))
                return@flow
            }
            val response = publicationRepository.fetchPublication(token = token, id = id)
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Http error"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Unexpected error"))
        }
    }
}