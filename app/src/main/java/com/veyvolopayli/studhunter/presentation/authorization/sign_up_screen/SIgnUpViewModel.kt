package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import androidx.lifecycle.*
import com.veyvolopayli.studhunter.common.emailIsValid
import com.veyvolopayli.studhunter.common.nameOrSurnameIsValid
import com.veyvolopayli.studhunter.common.passwordIsValid
import com.veyvolopayli.studhunter.common.usernameIsValid
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SIgnUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase
) : ViewModel() {

    private val _signUpResult = MutableLiveData<AuthorizationResult<Unit>>()
    val signUpResult: LiveData<AuthorizationResult<Unit>> = _signUpResult

    private val _signUpState = MutableLiveData(SignUpState())
    val signUpState: LiveData<SignUpState> = _signUpState

    private val _firstDataIsValid = MutableLiveData(false)
    val firstDataIsValid: LiveData<Boolean> = _firstDataIsValid

    private val _secondDataIsValid = MutableLiveData(false)
    val secondDataIsValid: LiveData<Boolean> = _secondDataIsValid

    fun signUp() {
        if (!_secondDataIsValid.value!!) return

        val state = _signUpState.value!!

        val signUpRequest = SignUpRequest(
            username = state.username,
            password = state.password,
            email = state.email,
            name = state.name,
            surname = state.surname,
            university = state.university
        )

        signUpByEmailUseCase(signUpRequest).onEach { authorizationResult ->
            _signUpResult.value = when (authorizationResult) {
                is AuthorizationResult.Authorized -> AuthorizationResult.Authorized()
                is AuthorizationResult.WrongData -> AuthorizationResult.WrongData()
                is AuthorizationResult.UnknownError -> AuthorizationResult.UnknownError()
                is AuthorizationResult.Error -> AuthorizationResult.UnknownError()
            }
        }.launchIn(viewModelScope)
    }

    fun textChanged(signUpTextField: SignUpTextField) {
        _signUpState.value = when (signUpTextField) {
            is SignUpTextField.Username -> {
                _signUpState.value!!.copy(username = signUpTextField.value)
            }
            is SignUpTextField.Password -> {
                _signUpState.value!!.copy(password = signUpTextField.value)
            }
            is SignUpTextField.Email -> {
                _signUpState.value!!.copy(email = signUpTextField.value)
            }
            is SignUpTextField.Name -> {
                _signUpState.value!!.copy(name = signUpTextField.value)
            }
            is SignUpTextField.Surname -> {
                _signUpState.value!!.copy(surname = signUpTextField.value)
            }
            is SignUpTextField.University -> {
                _signUpState.value!!.copy(university = signUpTextField.value)
            }
        }
    }

    fun firstPageListener() {
        val value = _signUpState.value!!
        if (
            value.username.usernameIsValid()
            && value.password.passwordIsValid()
            && value.email.emailIsValid()
        ) {
            if (_firstDataIsValid.value == false) _firstDataIsValid.value = true

        } else {
            if (_firstDataIsValid.value == true) _firstDataIsValid.value = false
        }
    }

    fun secondPageListener() {
        val value = _signUpState.value!!
        val name = value.name
        val surname = value.surname ?: ""
        val university = value.university ?: ""

        if (
            name.nameOrSurnameIsValid()
            && (if (surname.isNotEmpty()) surname.nameOrSurnameIsValid() else true)
            && (if (university.isNotEmpty()) university.nameOrSurnameIsValid() else true)
        ) {
            if (_secondDataIsValid.value == false) _secondDataIsValid.value = true
        } else {
            if (_secondDataIsValid.value == true) _secondDataIsValid.value = false
        }
    }

    sealed class SignUpTextField(val value: String) {
        class Username(value: String) : SignUpTextField(value)
        class Password(value: String) : SignUpTextField(value)
        class Email(value: String) : SignUpTextField(value)
        class Name(value: String) : SignUpTextField(value)
        class Surname(value: String) : SignUpTextField(value)
        class University(value: String) : SignUpTextField(value)
    }

}