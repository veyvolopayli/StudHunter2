package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository

class SignInByEmailUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(signInRequest: SignInRequest) = authRepository.signIn(signInRequest)
}