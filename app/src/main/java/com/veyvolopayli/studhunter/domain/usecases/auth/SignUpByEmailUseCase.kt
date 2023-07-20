package com.veyvolopayli.studhunter.domain.usecases.auth

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SignUpByEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(signUpRequest: SignUpRequest): Flow<AuthorizationResult<AuthResponse>> = flow {
        try {
            val response = authRepository.signUp(signUpRequest)
            prefs.edit().putString("jwt", "Bearer ${response.body()!!.token}").apply()
            emit(AuthorizationResult.Authorized())
        } catch (e: HttpException) {
            if (e.code() == 409) {
                e.printStackTrace()
                emit(AuthorizationResult.WrongData())
            }
            else {
                e.printStackTrace()
                emit(AuthorizationResult.Error(ErrorType.NetworkError))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(AuthorizationResult.Error(ErrorType.UnexpectedError))
        }
    }
}