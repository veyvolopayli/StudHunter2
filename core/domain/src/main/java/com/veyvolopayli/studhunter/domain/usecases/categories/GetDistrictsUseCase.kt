package com.veyvolopayli.studhunter.domain.usecases.categories

import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDistrictsUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository
) {
    operator fun invoke(): Flow<List<String>?> = flow {
        try {
            val districts = publicationRepository.getDistricts()
            emit(districts)
        } catch (e: Exception) {
            emit(null)
        }
    }
}