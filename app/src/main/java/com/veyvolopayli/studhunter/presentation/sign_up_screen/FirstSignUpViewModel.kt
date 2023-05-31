package com.veyvolopayli.studhunter.presentation.sign_up_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.domain.usecases.auth.IsEmailUniqueUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.IsUsernameUniqueUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FirstSignUpViewModel @Inject constructor(
    private val isUsernameUniqueUseCase: IsUsernameUniqueUseCase,
    private val isEmailUniqueUseCase: IsEmailUniqueUseCase
) : ViewModel() {

    private val _uniquenessResult = MutableLiveData<DataUniquenessResult>()
    val uniquenessResult: LiveData<DataUniquenessResult> = _uniquenessResult

    private val _signUpFieldsState = MutableLiveData(SignUpFieldsState())
    private val signUpFieldsState: LiveData<SignUpFieldsState> = _signUpFieldsState

    fun isUsernameUnique(username: String) {
        isUsernameUniqueUseCase(username).onEach { result ->
            _signUpFieldsState.value = when (result) {
                is DataUniquenessResult.Unique -> _signUpFieldsState.value?.copy(isUsernameOk = true)
                is DataUniquenessResult.NotUnique -> _signUpFieldsState.value?.copy(isUsernameOk = false)
                is DataUniquenessResult.Error -> _signUpFieldsState.value?.copy(isUsernameOk = false)
            }
        }
    }

    fun isEmailUnique(email: String) {
        isEmailUniqueUseCase(email).onEach { result ->
            _signUpFieldsState.value = when (result) {
                is DataUniquenessResult.Unique -> _signUpFieldsState.value?.copy(isEmailOk = true)
                is DataUniquenessResult.NotUnique -> _signUpFieldsState.value?.copy(isEmailOk = false)
                is DataUniquenessResult.Error -> _signUpFieldsState.value?.copy(isEmailOk = false)
            }
        }
    }

    data class SignUpFieldsState(
        val isUsernameOk: Boolean = true,
        val isPasswordOk: Boolean = true,
        val isEmailOk: Boolean = true
    )

}