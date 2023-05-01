package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest)

    suspend fun signIn(signInRequest: SignInRequest): SignInResponse
}