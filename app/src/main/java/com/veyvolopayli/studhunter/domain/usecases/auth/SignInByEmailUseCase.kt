package com.veyvolopayli.studhunter.domain.usecases.auth

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SignInByEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(signInRequest: SignInRequest): Flow<AuthorizationResult<AuthResponse>> = flow {
        try {
            val response = authRepository.signIn(signInRequest)
            prefs.edit().putString("jwt", "Bearer ${response.token}").apply()
            emit(AuthorizationResult.Authorized())
        } catch (e: HttpException) {
            if (e.code() == 409) {
                emit(AuthorizationResult.WrongData())
            }
            else {
                emit(AuthorizationResult.Error(ErrorType.NetworkError()))
            }
        } catch (e: Exception) {
            emit(AuthorizationResult.Error(ErrorType.LocalError()))
        }
    }
}