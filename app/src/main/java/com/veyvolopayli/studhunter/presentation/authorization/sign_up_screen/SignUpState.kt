package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val name: String = "",
    val surname: String? = null,
    val university: String? = null
)
