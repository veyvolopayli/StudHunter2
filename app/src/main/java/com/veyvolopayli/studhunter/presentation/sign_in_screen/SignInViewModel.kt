package com.veyvolopayli.studhunter.presentation.sign_in_screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase
import com.veyvolopayli.studhunter.presentation.MainActivity
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
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
    val state: LiveData<SignInState> = _state

    fun authenticate(context: Context, activity: FragmentActivity) {
        authenticateUseCase().onEach { result ->
            when (result) {
                is AuthResult.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
                is AuthResult.Authorized -> {
                    activity.supportFragmentManager.commit {
                        replace(R.id.main_fragment_container, HomeFragment())
                    }
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "UnknownError", Toast.LENGTH_SHORT).show()
                }
                is AuthResult.WrongPassword -> {
                    Toast.makeText(context, "WrongPassword", Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun signIn(signInRequest: SignInRequest, context: Context, activity: FragmentActivity) {
        signInByEmailUseCase(signInRequest).onEach { result ->

            when (result) {
                is AuthResult.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
                is AuthResult.Authorized -> {
                    activity.supportFragmentManager.commit {
                        replace(R.id.main_fragment_container, HomeFragment())
                    }
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                is AuthResult.WrongPassword -> {
                    Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show()
                }
            }

        }.launchIn(viewModelScope)
    }

}