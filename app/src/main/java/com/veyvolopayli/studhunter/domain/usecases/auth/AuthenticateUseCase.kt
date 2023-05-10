package com.veyvolopayli.studhunter.domain.usecases.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
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
        Log.e("PREFS", prefs.getString("jwt", null) ?: "null")

        try {
            val token = prefs.getString("jwt", null) ?: run {
                Log.e("prefs", "null")
                emit(AuthResult.Unauthorized())
                return@flow
            }
            Log.e("prefs", token)
            repository.authenticate()
            emit(AuthResult.Authorized())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e("prefs", "null")
                emit(AuthResult.Unauthorized())
            } else {
                Log.e("prefs", "null")
                emit(AuthResult.Unauthorized())
            }
        } catch (e: Exception) {
            Log.e("prefs", "null")
            emit(AuthResult.UnknownError())
        }
    }
}