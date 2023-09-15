package com.veyvolopayli.studhunter.presentation.publications_filter_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.categories.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PublicationsFilterViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _categories.value = resource.data?.values?.toList() ?: emptyList()
                }
                is Resource.Error -> {
                    _toast.value = "ERROR"
                }
            }
        }.launchIn(viewModelScope)
    }


}