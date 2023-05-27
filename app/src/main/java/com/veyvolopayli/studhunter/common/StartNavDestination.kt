package com.veyvolopayli.studhunter.common

sealed class StartNavDestination {
    object Auth : StartNavDestination()
    object Home : StartNavDestination()
}
