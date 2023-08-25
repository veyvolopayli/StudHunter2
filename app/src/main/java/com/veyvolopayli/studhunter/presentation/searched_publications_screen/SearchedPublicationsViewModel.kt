package com.veyvolopayli.studhunter.presentation.searched_publications_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.usecases.search.SearchPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchedPublicationsViewModel @Inject constructor(
    private val searchPublicationsUseCase: SearchPublicationsUseCase
) : ViewModel() {

    private val _searchedPublications = MutableLiveData<List<Publication>>()
    val searchedPublications: LiveData<List<Publication>> = _searchedPublications

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun searchPublications(query: String) {
        searchPublicationsUseCase(query).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _searchedPublications.value = resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    _error.value = "Error"
                }
            }
        }.launchIn(viewModelScope)
    }

}