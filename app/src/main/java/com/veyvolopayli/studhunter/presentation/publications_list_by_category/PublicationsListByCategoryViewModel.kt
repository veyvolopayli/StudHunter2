package com.veyvolopayli.studhunter.presentation.publications_list_by_category

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
class PublicationsListByCategoryViewModel @Inject constructor(
    private val getPublicationsByCategoryUseCase: GetPublicationsByCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData(PublicationsListState())
    val state: LiveData<PublicationsListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_PUBLICATION_CATEGORY)?.let { category ->
            getPublicationsByCategory(category)
        }
    }

    private fun getPublicationsByCategory(category: String) {
        getPublicationsByCategoryUseCase(category).onEach { result ->
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