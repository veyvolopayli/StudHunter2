package com.veyvolopayli.studhunter.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.get_publications.FetchPublicationsUseCase
import com.veyvolopayli.studhunter.presentation.home_screen.HomeViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPublicationsUseCase: FetchPublicationsUseCase
): ViewModel() {

    private val _state = MutableLiveData(HomeViewModelState())
    val state: LiveData<HomeViewModelState> = _state

    init {
        fetchPublications()
    }

    private fun fetchPublications() {
        fetchPublicationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeViewModelState(publications = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HomeViewModelState(
                        error = result.message ?: "Unexpected error"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeViewModelState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}