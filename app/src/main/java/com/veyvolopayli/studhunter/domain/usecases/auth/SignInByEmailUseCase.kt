package com.veyvolopayli.studhunter.domain.usecases.auth

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.AuthResult
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
    operator fun invoke(signInRequest: SignInRequest): Flow<AuthResult<AuthResponse>> = flow {
        try {
            val response = authRepository.signIn(signInRequest)
            prefs.edit().putString("jwt", "Bearer ${response.token}").apply()
            emit(AuthResult.Authorized(data = response))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                emit(AuthResult.Unauthorized())
            } else {
                emit(AuthResult.UnknownError())
            }
        } catch (e: Exception) {
            emit(AuthResult.UnknownError())
        }
    }
}