package com.veyvolopayli.studhunter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val checkUpdateUseCase: CheckUpdateUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainState())
    val state: LiveData<MainState> = _state

    val _authResult = MutableLiveData<AuthResult<Unit>>()
    val authResult: LiveData<AuthResult<Unit>> = _authResult

    val _checkUpdateResult = MutableLiveData<CheckUpdateResult<Unit>>()
    val checkUpdateResult: LiveData<CheckUpdateResult<Unit>> = _checkUpdateResult

    private fun checkUpdate() {
        checkUpdateUseCase().onEach { checkUpdateResult ->
            when (checkUpdateResult) {
                is CheckUpdateResult.UpdateAvailable -> {
                    _state.value = MainState(isLoading = false)
                }
                is CheckUpdateResult.LastVersionInstalled -> {
                    authenticate()
                }
                is CheckUpdateResult.Error -> {
                    _state.value = MainState(isLoading = false, isError = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun authenticate() {
        authenticateUseCase().onEach { authResult ->
            when (authResult) {
                is AuthResult.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
                is AuthResult.Authorized -> {
                    _authResult.value = AuthResult.Authorized()
                }
                is AuthResult.Unauthorized -> {

                }
                is AuthResult.UnknownError -> {

                }
                is AuthResult.WrongPassword -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}