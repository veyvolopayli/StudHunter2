package com.veyvolopayli.studhunter.presentation.sign_in_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.AuthorizationResult
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

    private val _signInResult = MutableLiveData<AuthorizationResult<Unit>>()
    val signInResult: LiveData<AuthorizationResult<Unit>> = _signInResult

    fun signIn(signInRequest: SignInRequest) {
        signInByEmailUseCase(signInRequest).onEach { result ->
            _signInResult.value = when (result) {
                is AuthorizationResult.Authorized -> AuthorizationResult.Authorized()
                is AuthorizationResult.WrongData -> AuthorizationResult.WrongData()
                is AuthorizationResult.UnknownError -> AuthorizationResult.UnknownError()
                is AuthorizationResult.Error -> AuthorizationResult.UnknownError()
            }
        }.launchIn(viewModelScope)
    }

}