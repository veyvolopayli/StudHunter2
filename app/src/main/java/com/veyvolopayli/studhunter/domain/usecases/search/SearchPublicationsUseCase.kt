package com.veyvolopayli.studhunter.domain.usecases.search

import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toPublication
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class SearchPublicationsUseCase @Inject constructor(
    private val repository: PublicationRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<Publication>>> = flow {
        try {
            val publications = repository.searchPublications(query).map { it.toPublication() }
            emit(Resource.Success(publications))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.NetworkError()))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }

}