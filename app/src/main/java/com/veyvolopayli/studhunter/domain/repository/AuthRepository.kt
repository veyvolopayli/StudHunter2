package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): AuthResult<Unit>
    suspend fun signIn(signInRequest: SignInRequest): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}