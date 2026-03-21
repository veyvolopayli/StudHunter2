package com.veyvolopayli.studhunter.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.internet.CheckInternetConnectionUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkInternet: CheckInternetConnectionUseCase,
    private val checkUpdate: CheckUpdateUseCase,
    private val authenticate: AuthenticateUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            if (!checkInternet()) {
                _state.value = UiState.NoInternet; return@launch
            }

            when (val upd = checkUpdate().first()) {
                is CheckUpdateResult.UpdateAvailable -> _state.value = UiState.UpdateAvailable
                is CheckUpdateResult.Error -> _state.value = UiState.Error(upd.error)
                is CheckUpdateResult.LastVersionInstalled -> {
                    authenticate().onEach { authResult ->
                        when (authResult) {
                            is AuthResult.Authorized -> _state.value = UiState.Authorized
                            is AuthResult.Unauthorized -> _state.value = UiState.NotAuthorized
                            is AuthResult.Error -> _state.value = UiState.Error(authResult.errorType)
                        }
                    }.launchIn(this)
                }
            }
        }
    }
}