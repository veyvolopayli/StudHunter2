package com.veyvolopayli.studhunter.presentation.universities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.usecases.user.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    private val _universities = MutableLiveData<List<String>>()
    val universities: LiveData<List<String>> = _universities

    init {
        getUniversities()
    }

    private fun getUniversities() {
        getUniversitiesUseCase().onEach { unis ->
            _universities.value = unis.map { it.shortName }
        }.launchIn(viewModelScope)
    }
}