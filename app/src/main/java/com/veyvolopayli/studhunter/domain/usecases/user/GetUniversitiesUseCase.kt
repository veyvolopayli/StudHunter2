package com.veyvolopayli.studhunter.domain.usecases.user

import com.veyvolopayli.studhunter.domain.model.University
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<University>> = flow {
        try {
            val universities = userRepository.getUniversities()
            emit(universities)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}