package com.veyvolopayli.studhunter.presentation.publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.publication.FetchPublicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val fetchPublicationUseCase: FetchPublicationUseCase
) : ViewModel() {

    private val _globalEvent = MutableLiveData<PublicationEvent>()
    val globalEvent: LiveData<PublicationEvent> = _globalEvent

    private val _state = MutableLiveData(PublicationState())
    val state: LiveData<PublicationState> = _state

    fun fetchPublication(id: String) {
        fetchPublicationUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _globalEvent.value = PublicationEvent.Loading
                }
                is Resource.Success -> {
                    _globalEvent.value = PublicationEvent.Success
                    _state.value = PublicationState(
                        category = resource.data?.body()?.category ?: "",
                        district = resource.data?.body()?.district ?: "",
                        description = resource.data?.body()?.description ?: "",
                        id = resource.data?.body()?.id ?: "",
                        imageUrl = resource.data?.body()?.imageUrl ?: "",
                        price = resource.data?.body()?.price ?: 0,
                        priceType = resource.data?.body()?.priceType ?: 0,
                        socials = resource.data?.body()?.socials ?: "",
                        timestamp = resource.data?.body()?.timestamp ?: "",
                        title = resource.data?.body()?.title ?: "",
                        userId = resource.data?.body()?.userId ?: ""
                    )
                }
                is Resource.Error -> {
                    _globalEvent.value = PublicationEvent.Error
                }
            }
        }
    }

}