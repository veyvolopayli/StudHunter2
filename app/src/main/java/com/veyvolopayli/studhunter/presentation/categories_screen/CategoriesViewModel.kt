package com.veyvolopayli.studhunter.presentation.categories_screen

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
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _categoriesState = MutableLiveData<Map<Int, String>>()
    val categoriesState: LiveData<Map<Int, String>> = _categoriesState

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _categoriesState.value = resource.data ?: emptyMap()
                }
                is Resource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}