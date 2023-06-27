package com.veyvolopayli.studhunter.presentation.districts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.usecases.categories.GetDistrictsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DistrictsViewModel @Inject constructor(
    private val getDistrictsUseCase: GetDistrictsUseCase
) : ViewModel() {

    private val _districts = MutableLiveData<List<String>>()
    val districts: LiveData<List<String>> = _districts

    init {
        getDistricts()
    }

    private fun getDistricts() {
        getDistrictsUseCase().onEach { districtsList ->
            districtsList?.let {
                _districts.value = it
            }
        }.launchIn(viewModelScope)
    }
}