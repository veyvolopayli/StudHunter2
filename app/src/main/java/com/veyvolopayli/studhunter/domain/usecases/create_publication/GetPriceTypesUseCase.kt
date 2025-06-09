package com.veyvolopayli.studhunter.domain.usecases.create_publication

import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetPriceTypesUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository
) {
    operator fun invoke(): Flow<Map<Int, String>?> = flow {
        try {
            val priceTypes = mapOf(1 to "р", 2 to "р/час")
            emit(priceTypes)
        } catch (e: Exception) {
            emit(null)
        }
    }
}