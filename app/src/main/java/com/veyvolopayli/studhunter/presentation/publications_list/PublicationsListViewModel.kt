package com.veyvolopayli.studhunter.presentation.publications_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationsListViewModel @Inject constructor(
    private val getPublicationsUseCase: GetPublicationsUseCase
) : ViewModel() {

    private val _state = MutableLiveData(PublicationsListState())
    val state: LiveData<PublicationsListState> = _state

    init {
        getPublications()
    }

    private fun getPublications() {
        getPublicationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PublicationsListState(publications = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PublicationsListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PublicationsListState(isLoading = true)
                }
            }
        }
    }

}