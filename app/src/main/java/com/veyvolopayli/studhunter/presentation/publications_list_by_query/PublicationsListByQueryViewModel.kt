package com.veyvolopayli.studhunter.presentation.publications_list_by_query

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsByCategoryUseCase
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsByQueryUseCase
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsUseCase
import com.veyvolopayli.studhunter.presentation.publications_list.PublicationsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationsListByQueryViewModel @Inject constructor(
    private val getPublicationsByQueryUseCase: GetPublicationsByQueryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData(PublicationsListState())
    val state: LiveData<PublicationsListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_PUBLICATION_QUERY)?.let { query ->
            getPublicationsByQuery(query)
        }
    }

    private fun getPublicationsByQuery(query: String) {
        getPublicationsByQueryUseCase(query).onEach { result ->
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