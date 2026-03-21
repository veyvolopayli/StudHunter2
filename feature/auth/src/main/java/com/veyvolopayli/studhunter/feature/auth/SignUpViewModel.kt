package com.veyvolopayli.studhunter.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthorizationResult
import com.veyvolopayli.studhunter.common.emailIsValid
import com.veyvolopayli.studhunter.common.nameOrSurnameIsValid
import com.veyvolopayli.studhunter.common.passwordIsValid
import com.veyvolopayli.studhunter.common.usernameIsValid
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val university: String = "",
    val isLoading: Boolean = false,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val emailError: String? = null,
    val nameError: String? = null,
    val surnameError: String? = null,
    val universityError: String? = null,
)

sealed interface SignUpEvent {
    data object NavigateToMain : SignUpEvent
    data class ShowError(val message: String) : SignUpEvent
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _events = Channel<SignUpEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onUsernameChange(v: String) = _uiState.update { it.copy(username = v, usernameError = null) }
    fun onPasswordChange(v: String) = _uiState.update { it.copy(password = v, passwordError = null) }
    fun onEmailChange(v: String) = _uiState.update { it.copy(email = v, emailError = null) }
    fun onNameChange(v: String) = _uiState.update { it.copy(name = v, nameError = null) }
    fun onSurnameChange(v: String) = _uiState.update { it.copy(surname = v, surnameError = null) }
    fun onUniversityChange(v: String) = _uiState.update { it.copy(university = v, universityError = null) }

    fun trySignUp() {
        val s = _uiState.value
        val usernameOk = s.username.usernameIsValid()
        val passwordOk = s.password.passwordIsValid()
        val emailOk = s.email.emailIsValid()
        val nameOk = s.name.nameOrSurnameIsValid()
        val surnameOk = s.surname.nameOrSurnameIsValid()
        val universityOk = s.university.isNotBlank()

        if (!usernameOk || !passwordOk || !emailOk || !nameOk || !surnameOk || !universityOk) {
            _uiState.update {
                it.copy(
                    usernameError = if (!usernameOk) "Некорректное имя пользователя" else null,
                    passwordError = if (!passwordOk) "Некорректный пароль" else null,
                    emailError = if (!emailOk) "Некорректная почта" else null,
                    nameError = if (!nameOk) "Странное имя" else null,
                    surnameError = if (!surnameOk) "Странная фамилия" else null,
                    universityError = if (!universityOk) "Выберите университет" else null,
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        val request = SignUpRequest(
            username = s.username.trim(),
            password = s.password.trim(),
            email = s.email.trim(),
            name = s.name.trim(),
            surname = s.surname.trim(),
            university = s.university.trim(),
        )

        signUpByEmailUseCase(request).onEach { result ->
            _uiState.update { it.copy(isLoading = false) }
            when (result) {
                is AuthorizationResult.Authorized -> _events.send(SignUpEvent.NavigateToMain)
                is AuthorizationResult.WrongData ->
                    _events.send(SignUpEvent.ShowError("Неверные данные"))
                is AuthorizationResult.Error ->
                    _events.send(SignUpEvent.ShowError("Ошибка сети. Попробуйте ещё раз"))
                is AuthorizationResult.UnknownError ->
                    _events.send(SignUpEvent.ShowError("Неизвестная ошибка"))
            }
        }.launchIn(viewModelScope)
    }
}
