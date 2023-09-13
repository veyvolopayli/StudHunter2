package com.veyvolopayli.studhunter.presentation.publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.millsToDateTime
import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.usecases.publication.AddToFavoriteUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.CheckFavoriteStatusUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.FetchPublicationUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.FetchUserByIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.ImageUrlValidityUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.RemoveFromFavoriteUserCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val fetchPublicationUseCase: FetchPublicationUseCase,
    private val imageUrlValidityUseCase: ImageUrlValidityUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUserCase: RemoveFromFavoriteUserCase,
    private val checkFavoriteStatusUseCase: CheckFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PublicationScreenState>()
    val state: LiveData<PublicationScreenState> = _state

    private val _imagesState = MutableLiveData<List<String>>()
    val imagesState: LiveData<List<String>> = _imagesState

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private fun checkIsInFavorite(pubID: String) {
        checkFavoriteStatusUseCase(pubID).onEach {
            if (it is Resource.Success) {
                _isFavorite.value = it.data ?: false
            }
        }.launchIn(viewModelScope)
    }

    fun fetchAllData(pubID: String) {
        checkIsInFavorite(pubID)
        getData(pubID)
    }

    private fun getData(pubID: String) {
        fetchPublicationUseCase(pubID).onEach { resource: Resource<DetailedPublication> ->
            if (resource is Resource.Success) {
                resource.data?.let { detailedPublication ->
                    val publication = detailedPublication.publication
                    val user = detailedPublication.user

                    _state.value = PublicationScreenState(
                        publicationID = publication.id,
                        price = publication.price,
                        priceType = publication.priceType,
                        title = publication.title,
                        description = publication.description,
                        userId = user.id,
                        userFullName = "${ user.name } ${ user.surname }",
                        userRating = user.rating,
                        username = user.username,
                        isLoading = false,
                        isUserOwner = detailedPublication.userIsOwner,
                        date = publication.timestamp.millsToDateTime()
                    )

                }
            }
        }.launchIn(viewModelScope)

        imageUrlValidityUseCase(pubID).onEach { resource ->
            if (resource is Resource.Success) {
                val images = resource.data ?: emptyList()
                _imagesState.value = images
            }
        }.launchIn(viewModelScope)
    }

    fun changeFavorite() {
        _isFavorite.value?.let { favorite ->
            if (favorite) {
                removeFromFavoriteUserCase(_state.value?.publicationID ?: "").onEach {
                    if (it == true) {
                        _isFavorite.value = false
                    }
                }.launchIn(viewModelScope)
            } else {
                addToFavoriteUseCase(_state.value?.publicationID ?: "").onEach {
                    if (it == true) {
                        _isFavorite.value = true
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}