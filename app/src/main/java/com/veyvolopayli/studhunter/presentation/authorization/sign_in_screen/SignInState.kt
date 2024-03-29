package com.veyvolopayli.studhunter.presentation.authorization.sign_in_screen

data class SignInState(
    val username: String = "",
    val password: String = "",
    val dataIsValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
    val authorized: Boolean = false
)
