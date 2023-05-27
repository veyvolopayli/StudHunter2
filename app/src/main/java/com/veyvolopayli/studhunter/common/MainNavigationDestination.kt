package com.veyvolopayli.studhunter.common

sealed class MainNavigationDestination {
    object Home : MainNavigationDestination()
    object Categories : MainNavigationDestination()
    object NewPublication : MainNavigationDestination()
    object Favorites : MainNavigationDestination()
    object Profile : MainNavigationDestination()
}
