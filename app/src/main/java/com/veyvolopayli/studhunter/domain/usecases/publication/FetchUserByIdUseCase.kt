package com.veyvolopayli.studhunter.domain.usecases.publication

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants.JWT
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class FetchUserByIdUseCase @Inject constructor(
    private val repository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(id: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val token = prefs.getString(JWT, null) ?: run {
                emit(Resource.Error("Unauthorized"))
                return@flow
            }
            val user = repository.fetchUserById(token = token, id = id)
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error("Check your internet connection"))
            return@flow
        } catch (e: Exception) {
            emit(Resource.Error("Some unexpected error occurred"))
        }
    }
}