package com.veyvolopayli.studhunter.domain.usecases.publication

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class CheckFavoriteStatusUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(pubID: String): Flow<Resource<Boolean>> = flow {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val isInFavorite = publicationRepository.checkPubFavoriteStatus(token = token, pubID = pubID)
            emit(Resource.Success(isInFavorite))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.ServerError(e.localizedMessage ?: "")))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError(e.localizedMessage ?: "")))
        }
    }
}