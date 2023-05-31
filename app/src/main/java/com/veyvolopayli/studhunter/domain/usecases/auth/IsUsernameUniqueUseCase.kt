package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.presentation.sign_up_screen.DataUniquenessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsUsernameUniqueUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(username: String): Flow<DataUniquenessResult> = flow {
        try {
            val isUsernameUnique = repository.isUsernameUnique(username)
            if (isUsernameUnique) emit(DataUniquenessResult.Unique) else emit(DataUniquenessResult.NotUnique)
        } catch (e: Exception) {
            emit(DataUniquenessResult.Error)
        }
    }
}