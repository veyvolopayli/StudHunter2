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
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
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

    /*private val _dataState = MutableLiveData<PublicationScreenState>()
    val dataState: LiveData<PublicationScreenState> = _dataState

    private val _imagesState = MutableLiveData<List<String>>()
    val imagesState: LiveData<List<String>> = _imagesState

    private val _userState = MutableLiveData<User>()
    val userState: LiveData<User> = _userState

    private val _userIsOwnerState = MutableLiveData<Boolean>()
    val userIsOwnerState: LiveData<Boolean> = _userIsOwnerState

    private val _favorite = MutableLiveData<Boolean>(false)
    val favorite: LiveData<Boolean> = _favorite

    private var favoriteTimerJob: Job? = null

    fun fetchPublication(id: String) {
        fetchPublicationUseCase(id).onEach { resource ->
            if (resource is Resource.Success) {
                resource.data?.body()?.let { publication ->
                    _dataState.value = PublicationScreenState(
                        category = publication.category,
                        district = publication.district,
                        description = publication.description,
                        publicationID = publication.id,
                        price = publication.price,
                        priceType = publication.priceType,
                        socials = publication.socials,
                        date = publication.timestamp.millsToDateTime(),
                        title = publication.title,
                        userId = publication.userId
                    )
                    userIsOwner(publication.userId)
                    fetchUser(publication.userId)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchImages(publicationId: String) {
        imageUrlValidityUseCase(publicationId).onEach { resource ->
            if (resource is Resource.Success) {
                resource.data?.let { urls ->
                    _imagesState.value = urls
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchUser(userId: String) {
        fetchUserByIdUseCase(userId).onEach { resource ->
            if (resource is Resource.Success) {
                resource.data?.let { user ->
                    _userState.value = user
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun userIsOwner(userId: String) {
        getCurrentUserIdUseCase().onEach { currentUserID ->
            _userIsOwnerState.value = currentUserID == userId
        }.launchIn(viewModelScope)
    }

    private fun addToFavorite(pubID: String) {
        addToFavoriteUseCase(pubID).onEach {
            if (it == null) {
                _favorite.value = favorite.value?.not()
            }
        }.launchIn(viewModelScope)
    }

    private fun removeFromFavorite(pubID: String) {
        removeFromFavoriteUserCase(pubID).onEach {
            if (it == null) {
                _favorite.value = favorite.value?.not()
            }
        }.launchIn(viewModelScope)
    }

    fun checkFavorite(pubID: String) {
        checkFavoriteStatusUseCase(pubID = pubID).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _favorite.value = resource.data ?: false
                }
                is Resource.Error -> {
                    when(resource.error ?: ErrorType.LocalError()) {
                        is ErrorType.ServerError -> {

                        }
                        is ErrorType.LocalError -> {

                        }
                        is ErrorType.Unauthorized -> {

                        }
                        is ErrorType.NetworkError -> {

                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    val onFavoriteClick: (String) -> Unit = { pubID ->
        _favorite.value = favorite.value?.not()

        favoriteTimerJob?.cancel()

        favoriteTimerJob = viewModelScope.launch {
            delay(1000)

            if (favorite.value == false) {
                removeFromFavorite(pubID = pubID)
            } else if (favorite.value == true) {
                addToFavorite(pubID = pubID)
            }
        }
    }*/
}