package com.veyvolopayli.studhunter.presentation.sign_in_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInByEmailUseCase: SignInByEmailUseCase,
    private val authenticateUseCase: AuthenticateUseCase
): ViewModel() {

    private val _state = MutableLiveData(SignInState())
//    val state: LiveData<SignInState> = _state

    private val _signInResult = MutableLiveData<AuthResult<Unit>>()
    val signInResult: LiveData<AuthResult<Unit>> = _signInResult

    fun signIn(signInRequest: SignInRequest) {
        signInByEmailUseCase(signInRequest).onEach { result ->

            when (result) {
                is AuthResult.Loading -> {
                    _signInResult.value = AuthResult.Loading()
                }
                is AuthResult.Authorized -> {
                    _signInResult.value = AuthResult.Authorized()
                }
                is AuthResult.Unauthorized -> {
                    _signInResult.value = AuthResult.Unauthorized()
                }
                is AuthResult.UnknownError -> {
                    _signInResult.value = AuthResult.UnknownError()
                }
                is AuthResult.WrongPassword -> {
                    _signInResult.value = AuthResult.WrongPassword()
                }
            }

        }.launchIn(viewModelScope)
    }

}