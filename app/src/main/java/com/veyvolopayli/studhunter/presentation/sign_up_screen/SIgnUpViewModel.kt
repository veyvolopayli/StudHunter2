package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.AuthorizationResult
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SIgnUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase
) : ViewModel() {

    private val _state = MutableLiveData(SignUpState())
    val state: LiveData<SignUpState> = _state

    val _signUpResult = MutableLiveData<AuthorizationResult<Unit>>()
    val signUpResult: LiveData<AuthorizationResult<Unit>> = _signUpResult

    fun signUp(signUpRequest: SignUpRequest) {
        signUpByEmailUseCase(signUpRequest).onEach { result ->
            _signUpResult.value = when (result) {
                is AuthorizationResult.Authorized -> AuthorizationResult.Authorized()
                is AuthorizationResult.WrongData -> AuthorizationResult.WrongData()
                is AuthorizationResult.UnknownError -> AuthorizationResult.UnknownError()
                is AuthorizationResult.Error -> AuthorizationResult.UnknownError()
            }
        }.launchIn(viewModelScope)
    }

}