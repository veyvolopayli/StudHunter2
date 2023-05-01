package com.veyvolopayli.studhunter.domain.usecases

import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.repository.AuthRepository

class SignUpByEmailUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(signUpRequest: SignUpRequest) = authRepository.signUp(signUpRequest)
}