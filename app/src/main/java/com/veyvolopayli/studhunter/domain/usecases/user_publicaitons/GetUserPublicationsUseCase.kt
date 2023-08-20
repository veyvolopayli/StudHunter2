package com.veyvolopayli.studhunter.domain.usecases.user_publicaitons

import android.util.Log
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toPublication
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetUserPublicationsUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userID: String) = flow {
        try {
            val publications = repository.getUserPublications(userID).map { it.toPublication() }
            emit(Resource.Success(publications))
        } catch (e: HttpException) {
            Log.e("USER_PUBLICATIONS", e.localizedMessage ?: "")
            emit(Resource.Error(ErrorType.NetworkError(e.localizedMessage ?: "")))
        } catch (e: Exception) {
            Log.e("USER_PUBLICATIONS", e.localizedMessage ?: "")
            emit(Resource.Error(ErrorType.LocalError(e.localizedMessage ?: "")))
        }
    }
}