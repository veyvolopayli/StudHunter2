package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class EmailUniquenessUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Boolean> = flow {
        try {
            repository.isEmailUnique(email)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}