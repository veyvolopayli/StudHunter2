package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import com.veyvolopayli.studhunter.presentation.main.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke(): Flow<AuthResult<Unit>> = flow {
        val token = prefsRepository.getJwtToken() ?: run {
            emit(AuthResult.Unauthorized<Unit>())
            return@flow
        }
        authRepository.authenticate(token)
        emit(AuthResult.Authorized())
    }.catch { e ->
        val result = when (e) {
            is HttpException -> when (e.code()) {
                401 -> AuthResult.Unauthorized<Unit>()
                409 -> AuthResult.Error(ErrorType.ServerError())
                else -> AuthResult.Error(ErrorType.NetworkError())
            }
            else -> AuthResult.Error(ErrorType.LocalError())
        }
        (e as? Exception)?.printStackTrace()
        emit(result)
    }
}