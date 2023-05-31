package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.presentation.sign_up_screen.DataUniquenessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsEmailUniqueUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<DataUniquenessResult> = flow {
        try {
            val isEmailUnique = authRepository.isEmailUnique(email)
            if (isEmailUnique) emit(DataUniquenessResult.Unique) else emit(DataUniquenessResult.NotUnique)
        } catch (e: Exception) {
            emit(DataUniquenessResult.Error)
        }
    }
}