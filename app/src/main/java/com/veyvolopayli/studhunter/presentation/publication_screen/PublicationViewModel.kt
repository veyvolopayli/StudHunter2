package com.veyvolopayli.studhunter.presentation.publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.toDetailedPublication
import com.veyvolopayli.studhunter.domain.usecases.publication.FetchPublicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val fetchPublicationUseCase: FetchPublicationUseCase
) : ViewModel() {

    private val _globalEvent = MutableLiveData<PublicationEvent>()
    val globalEvent: LiveData<PublicationEvent> = _globalEvent

    private val _state = MutableLiveData<PublicationState>()
    val state: LiveData<PublicationState> = _state

    fun fetchPublication(id: String) {
        fetchPublicationUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _globalEvent.value = PublicationEvent.Loading
                }
                is Resource.Success -> {
                    _globalEvent.value = PublicationEvent.Success
                    val publication = resource.data?.body()?.toDetailedPublication() ?: run {
                        return@onEach
                    }
                    _state.value = PublicationState(
                        category = publication.category,
                        district = publication.district,
                        description = publication.description,
                        id = publication.id,
                        images = listOf(publication.imageUrl, publication.imageUrl.replaceAfterLast('_', "1"), publication.imageUrl.replaceAfterLast('_', "2")),
                        price = publication.price,
                        priceType = publication.priceType,
                        socials = publication.socials,
                        timestamp = publication.timestamp,
                        title = publication.title,
                        userId = publication.userId
                    )
                }
                is Resource.Error -> {
                    _globalEvent.value = PublicationEvent.Error
                }
            }
        }.launchIn(viewModelScope)
    }

}