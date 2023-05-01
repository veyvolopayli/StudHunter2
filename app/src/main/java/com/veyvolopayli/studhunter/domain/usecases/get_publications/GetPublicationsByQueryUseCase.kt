package com.veyvolopayli.studhunter.domain.usecases.get_publications

import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toPublication
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPublicationsByQueryUseCase @Inject constructor(
    private val repository: PublicationRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Publication>>> = flow {
        try {
            emit(Resource.Loading<List<Publication>>())
            val publications =
                repository.getPublicationsByQuery(query = query).map { it.toPublication() }
            emit(Resource.Success<List<Publication>>(publications))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Publication>>(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Publication>>(message = "Couldn't reach server. Check your internet connection"))
        }
    }
}