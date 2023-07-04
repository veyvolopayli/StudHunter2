package com.veyvolopayli.studhunter.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkUpdateUseCase: CheckUpdateUseCase,
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isBottomBarVisible = MutableStateFlow(false)
    val isBottomBarVisible = _isBottomBarVisible.asStateFlow()

    private val _launchAppResult = MutableLiveData<LaunchAppResult<ErrorType>>()
    val launchAppResult: LiveData<LaunchAppResult<ErrorType>> = _launchAppResult

    init {
        checkUpdate()
    }

    private fun checkUpdate() {
        checkUpdateUseCase().onEach { result ->
            when (result) {
                is CheckUpdateResult.UpdateAvailable -> {
                    _launchAppResult.value = LaunchAppResult.NeedToUpdate()
                }
                is CheckUpdateResult.Error -> {
                    _launchAppResult.value = LaunchAppResult.ErrorOccurred(result.error)
                }
                is CheckUpdateResult.LastVersionInstalled -> {
                    authenticate()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun authenticate() {
        authenticateUseCase().onEach { result ->
            _launchAppResult.value = when (result) {
                is AuthResult.Authorized -> LaunchAppResult.Ok()
                is AuthResult.Unauthorized -> LaunchAppResult.NeedToAuthorize()
                is AuthResult.Error -> LaunchAppResult.ErrorOccurred(result.errorType)
            }
        }.launchIn(viewModelScope)
    }

    fun appLaunched() {
        _isLoading.value = false
    }

    fun showBottomBar() {
        _isBottomBarVisible.value = true
    }

    fun hideBottomBar() {
        _isBottomBarVisible.value = false
    }

    fun launchAppOk() {
        _launchAppResult.value = LaunchAppResult.Ok()
    }



}