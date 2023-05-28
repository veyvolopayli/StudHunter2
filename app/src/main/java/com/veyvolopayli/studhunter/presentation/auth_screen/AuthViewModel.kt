package com.veyvolopayli.studhunter.presentation.auth_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    /*private val _authResult = MutableLiveData<AuthResult<Unit>>()
    val authResult: LiveData<AuthResult<Unit>> = _authResult

    init {
        authenticate()
    }

    private fun authenticate() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    _authResult.value = AuthResult.Authorized()
                }
                is AuthResult.Unauthorized -> {
                    _authResult.value = AuthResult.Unauthorized()
                }
                is AuthResult.Error -> {
                    _authResult.value = AuthResult.Error()
                }
                is AuthResult.WrongPassword -> {
                    _authResult.value = AuthResult.WrongPassword()
                }
            }
        }.launchIn(viewModelScope)
    }*/

}