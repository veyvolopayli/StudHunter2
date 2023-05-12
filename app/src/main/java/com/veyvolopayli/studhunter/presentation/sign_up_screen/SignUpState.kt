package com.veyvolopayli.studhunter.presentation.sign_up_screen

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val name: String = "",
    val surname: String? = null,
    val university: String? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
