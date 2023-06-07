package com.veyvolopayli.studhunter.presentation.main

sealed class MainNavDestination {
    object Home : MainNavDestination()
    object Categories : MainNavDestination()
    object Upload : MainNavDestination()
    object Favorites : MainNavDestination()
    object Profile : MainNavDestination()
    object Search : MainNavDestination()
    object Filter : MainNavDestination()
}
