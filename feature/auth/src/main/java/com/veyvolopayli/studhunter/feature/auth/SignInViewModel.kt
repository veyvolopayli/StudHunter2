package com.veyvolopayli.studhunter.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.AuthorizationResult
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface SignInEvent {
    data object NavigateToMain : SignInEvent
    data class ShowError(val message: String) : SignInEvent
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInByEmailUseCase: SignInByEmailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _events = Channel<SignInEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value.copy(username = value, errorMessage = null)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value, errorMessage = null)
    }

    fun signIn() {
        val state = _uiState.value
        val request = SignInRequest(username = state.username.trim(), password = state.password.trim())
        _uiState.value = state.copy(isLoading = true, errorMessage = null)

        signInByEmailUseCase(request).onEach { result ->
            _uiState.value = _uiState.value.copy(isLoading = false)
            when (result) {
                is AuthorizationResult.Authorized -> _events.send(SignInEvent.NavigateToMain)
                is AuthorizationResult.WrongData ->
                    _events.send(SignInEvent.ShowError("Неправильный логин или пароль"))
                is AuthorizationResult.Error ->
                    _events.send(SignInEvent.ShowError("Ошибка сети. Попробуйте ещё раз"))
                is AuthorizationResult.UnknownError ->
                    _events.send(SignInEvent.ShowError("Неизвестная ошибка"))
            }
        }.launchIn(viewModelScope)
    }
}
