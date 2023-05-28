package com.veyvolopayli.studhunter.domain.usecases.auth

import android.content.SharedPreferences
import android.util.Log
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(): Flow<AuthResult<Unit>> = flow {

        try {
            val token = prefs.getString("jwt", null) ?: run {
                emit(AuthResult.Unauthorized())
                return@flow
            }
            repository.authenticate(token)
            emit(AuthResult.Authorized())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                emit(AuthResult.Unauthorized())
            }
            else if (e.code() == 409) {
                emit(AuthResult.Error(ErrorType.ServerError))
            }
            else {
                emit(AuthResult.Error(ErrorType.NetworkError))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(ErrorType.UnexpectedError))
        }
    }
}