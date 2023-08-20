package com.veyvolopayli.studhunter.domain.usecases.my_publications

import android.content.SharedPreferences
import android.util.Log
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toPublication
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetMyPublicationsUseCase @Inject constructor(
    private val repository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke() = flow {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val myPublications = repository.getMyPublications(token = token)
            emit(Resource.Success(myPublications))
        } catch (e: HttpException) {
            Log.e("MY_PUBLICATIONS", e.localizedMessage ?: "")
            emit(Resource.Error(ErrorType.NetworkError(e.localizedMessage ?: "")))
        } catch (e: Exception) {
            Log.e("MY_PUBLICATIONS", e.localizedMessage ?: "")
            emit(Resource.Error(ErrorType.LocalError(e.localizedMessage ?: "")))
        }
    }
}