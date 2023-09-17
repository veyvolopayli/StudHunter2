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
                    val categories = resource.data?.values?.toList()?.sortedBy {
                        it.length }?.toMutableList() ?: mutableListOf()
                    if (categories.isNotEmpty()) {
                        val preLastElement = categories[categories.size - 2]
                        categories.remove(preLastElement)
                        categories.add(preLastElement)
                    }
                    _categories.value =  categories
                }
                is Resource.Error -> {
                    _toast.value = "ERROR"
                }
            }
        }.launchIn(viewModelScope)
    }


}