package com.veyvolopayli.studhunter.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.get_publications.FetchPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPublicationsUseCase: FetchPublicationsUseCase
): ViewModel() {

    private val _state = MutableLiveData(HomeState())
    val state: LiveData<HomeState> = _state

    private val _event = MutableLiveData<HomeEvent>(HomeEvent.Loading)
    val event: LiveData<HomeEvent> = _event

//    private var _recyclerState: Parcelable? = null
//    val recyclerState = _recyclerState

    init {
        fetchPublications()
    }

    private fun fetchPublications() {
        fetchPublicationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(publications = result.data ?: emptyList())
                    _event.value = HomeEvent.Success
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = ""
                    )
                    _event.value = HomeEvent.Error
                }
            }
        }.launchIn(viewModelScope)
    }

    /*fun saveRecyclerState(state: Parcelable?) {
        _recyclerState = state
    }*/
}