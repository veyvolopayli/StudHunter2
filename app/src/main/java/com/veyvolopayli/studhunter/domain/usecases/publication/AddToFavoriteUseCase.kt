package com.veyvolopayli.studhunter.domain.usecases.publication

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(pubID: String) = flow {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Unit)
                return@flow
            }
            val changePubFavoriteStatusRequest = ChangePubFavoriteStatusRequest(publicationId = pubID)
            publicationRepository.addPubToFavorite(token, changePubFavoriteStatusRequest)
        } catch (e: Exception) {
            emit(null)
        }
    }
}