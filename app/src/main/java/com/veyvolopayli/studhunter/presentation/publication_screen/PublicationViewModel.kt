package com.veyvolopayli.studhunter.presentation.publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toDetailedPublication
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.usecases.publication.FetchPublicationUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.FetchUserByIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.publication.ImageUrlValidityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val fetchPublicationUseCase: FetchPublicationUseCase,
    private val imageUrlValidityUseCase: ImageUrlValidityUseCase,
    private val fetchUserByIdUseCase: FetchUserByIdUseCase
) : ViewModel() {

    private val _dataState = MutableLiveData<PublicationState>()
    val dataState: LiveData<PublicationState> = _dataState

    private val _imagesState = MutableLiveData<List<String>>()
    val imagesState: LiveData<List<String>> = _imagesState

    private val _userState = MutableLiveData<User>()
    val userState: LiveData<User> = _userState

    fun fetchPublication(id: String) {
        fetchPublicationUseCase(id).onEach { resource ->
            if (resource is Resource.Success) {
                resource.data?.body()?.let { publication ->
                    _dataState.value = PublicationState(
                        category = publication.category,
                        district = publication.district,
                        description = publication.description,
                        id = publication.id,
                        price = publication.price,
                        priceType = publication.priceType,
                        socials = publication.socials,
                        timestamp = publication.timestamp,
                        title = publication.title,
                        userId = publication.userId
                    )
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

}