package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInByEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signInRequest: SignInRequest): Flow<Resource<SignInResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = authRepository.signIn(signInRequest)
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Failed to reach server. Check your internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}