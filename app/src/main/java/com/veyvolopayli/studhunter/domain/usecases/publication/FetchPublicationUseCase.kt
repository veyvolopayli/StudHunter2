package com.veyvolopayli.studhunter.domain.usecases.publication

import android.content.SharedPreferences
import android.util.Log
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import javax.inject.Inject

class FetchPublicationUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(id: String): Flow<Resource<DetailedPublication>> = flow {
        try {
            val token = prefs.getString("jwt", null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val detailedPublication = publicationRepository.fetchPublication(token = token, id = id)
            Log.e("TAG", Json.encodeToString(detailedPublication))
            emit(Resource.Success(data = detailedPublication))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.NetworkError()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}