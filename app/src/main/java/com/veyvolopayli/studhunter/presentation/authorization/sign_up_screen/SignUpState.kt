package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

data class SignUpState(
    val isLoading: Boolean = false,
    val isEmailUnique: Boolean? = null,
    val isUsernameUnique: Boolean? = null
)
