package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen.DataUniquenessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class EmailUniquenessUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<DataUniquenessResult> = flow {
        try {
            authRepository.isEmailUnique(email)
            emit(DataUniquenessResult.NotUnique)
        } catch (e: HttpException) {
            if (e.code() == 400) {
                emit(DataUniquenessResult.Unique)
            }
            else {
                emit(DataUniquenessResult.Error)
            }
            return@flow
        } catch (e: Exception) {
            emit(DataUniquenessResult.Error)
        }
    }
}