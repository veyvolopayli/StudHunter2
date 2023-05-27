package com.veyvolopayli.studhunter.presentation.start_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val checkUpdateUseCase: CheckUpdateUseCase
) : ViewModel() {

    private val _checkUpdateResult = MutableLiveData<CheckUpdateResult<Unit>>()
    val checkUpdateResult: LiveData<CheckUpdateResult<Unit>> = _checkUpdateResult

    init {
        checkUpdate()
    }

    private fun checkUpdate() {
        checkUpdateUseCase().onEach { result ->
            when (result) {
                is CheckUpdateResult.UpdateAvailable -> {
                    _checkUpdateResult.value = CheckUpdateResult.UpdateAvailable()
                }
                is CheckUpdateResult.LastVersionInstalled -> {
                    _checkUpdateResult.value = CheckUpdateResult.LastVersionInstalled()
                }
                is CheckUpdateResult.Error -> {
                    _checkUpdateResult.value = CheckUpdateResult.Error()
                }
            }
        }.launchIn(viewModelScope)
    }

}