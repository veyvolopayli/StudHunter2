package com.veyvolopayli.studhunter.domain.usecases.get_publications

import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.FilterRequest
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilteredPublicationsUseCase @Inject constructor(
    private val repository: PublicationRepository
) {
    operator fun invoke(filterRequest: FilterRequest) = flow<Resource<List<Publication>>> {
        try {
            val publications = repository.getFilteredPublications(filterRequest)
            emit(Resource.Success(publications))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}