package com.veyvolopayli.studhunter.presentation.auth_screen

sealed class AuthNavDestination {
    object SignUp : AuthNavDestination()
    object SignIn : AuthNavDestination()
}