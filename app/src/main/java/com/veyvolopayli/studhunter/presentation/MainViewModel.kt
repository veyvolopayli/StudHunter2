package com.veyvolopayli.studhunter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val checkUpdateUseCase: CheckUpdateUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainState())
    val state: LiveData<MainState> = _state

    private val _authResult = MutableLiveData<AuthResult<Unit>>()
    val authResult: LiveData<AuthResult<Unit>> = _authResult

    private val _checkUpdateResult = MutableLiveData<CheckUpdateResult<Unit>>()
    val checkUpdateResult: LiveData<CheckUpdateResult<Unit>> = _checkUpdateResult

    init {
        checkUpdate()
    }

    private fun checkUpdate() {
        checkUpdateUseCase().onEach { checkUpdateResult ->
            when (checkUpdateResult) {
                is CheckUpdateResult.UpdateAvailable -> {
                    _state.value = MainState(isLoading = false)
                    _checkUpdateResult.value = CheckUpdateResult.UpdateAvailable()
                }
                is CheckUpdateResult.LastVersionInstalled -> {
                    _checkUpdateResult.value = CheckUpdateResult.LastVersionInstalled()
                    authenticate()
                }
                is CheckUpdateResult.Error -> {
                    _checkUpdateResult.value = CheckUpdateResult.Error()
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