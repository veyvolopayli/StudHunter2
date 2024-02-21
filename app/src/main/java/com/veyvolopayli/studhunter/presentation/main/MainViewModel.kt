package com.veyvolopayli.studhunter.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.CheckUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkUpdateUseCase: CheckUpdateUseCase,
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _launchAppResult = MutableLiveData<LaunchAppResult>()
    val launchAppResult: LiveData<LaunchAppResult> = _launchAppResult

    init {
        checkUpdate {
            authenticate()
        }
    }

    private fun checkUpdate(onClearLaunch: suspend () -> Unit) {
        viewModelScope.launch {
            checkUpdateUseCase().collect { result ->
                when (result) {
                    is CheckUpdateResult.UpdateAvailable -> {
                        _launchAppResult.value = LaunchAppResult.UpdateAvailable()
                    }
                    is CheckUpdateResult.Error -> {
                        _launchAppResult.value = LaunchAppResult.Error(result.error)
                    }
                    is CheckUpdateResult.LastVersionInstalled -> {
                        onClearLaunch()
                    }
                }
            }
        }
    }

    private suspend fun authenticate() {
        authenticateUseCase().collect { result ->
            _launchAppResult.value = when (result) {
                is AuthResult.Authorized -> LaunchAppResult.Success()
                is AuthResult.Unauthorized -> LaunchAppResult.NotAuthorized()
                is AuthResult.Error -> LaunchAppResult.Error(result.errorType)
            }
//            _isLoading.value = false
        }
    }

    fun stopLoading() {
        _isLoading.value = false
    }
}