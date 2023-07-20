package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.usecases.auth.EmailUniquenessUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.UsernameUniquenessUseCase
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase,
    private val usernameUniquenessUseCase: UsernameUniquenessUseCase,
    private val emailUniquenessUseCase: EmailUniquenessUseCase
) : ViewModel() {

    private val _signUpResult = MutableLiveData<AuthorizationResult<AuthResponse>>()
    val signUpResult: LiveData<AuthorizationResult<AuthResponse>> = _signUpResult

    private val _state = MutableLiveData(SignUpState())
    val state: LiveData<SignUpState> = _state

    private var signUpRequest: SignUpRequest? = null

    fun signUp() {
        signUpRequest?.let { _signUpRequest ->
            signUpByEmailUseCase(_signUpRequest).onEach { authorizationResult ->
                _signUpResult.value = authorizationResult
                if (authorizationResult !is AuthorizationResult.Authorized) {
                    _state.value?.let {
                        _state.value = it.copy(isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun checkUniqueness(
        username: String, password: String, email: String,
        name: String, surname: String?, university: String
    ) {
        signUpRequest = SignUpRequest(
            username = username,
            password = password,
            email = email,
            name = name,
            surname = surname,
            university = university
        )
        checkUsernameUniqueness(username)
        checkEmailUniqueness(email)
    }

    private fun checkUsernameUniqueness(username: String) {
        usernameUniquenessUseCase(username = username).onEach { dataUniquenessResult ->
            when (dataUniquenessResult) {
                is DataUniquenessResult.Unique -> {
                    _state.value?.let {
                        _state.value = it.copy(isUsernameUnique = true, isLoading = true)
                    }
                }

                is DataUniquenessResult.NotUnique -> {
                    _state.value?.let {
                        _state.value = it.copy(isUsernameUnique = false, isLoading = false)
                    }
                }

                is DataUniquenessResult.Error -> {
                    _state.value?.let {
                        _state.value = it.copy(isUsernameUnique = null, isLoading = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkEmailUniqueness(email: String) {
        emailUniquenessUseCase(email = email).onEach { dataUniquenessResult ->
            when (dataUniquenessResult) {
                is DataUniquenessResult.Unique -> {
                    _state.value?.let {
                        _state.value = it.copy(isEmailUnique = true)
                    }
                }

                is DataUniquenessResult.NotUnique -> {
                    _state.value?.let {
                        _state.value = it.copy(isEmailUnique = false, isLoading = false)
                    }
                }

                is DataUniquenessResult.Error -> {
                    _state.value?.let {
                        _state.value = it.copy(isEmailUnique = null, isLoading = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}