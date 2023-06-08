package com.veyvolopayli.studhunter.presentation.main

import androidx.fragment.app.Fragment

sealed class MainNavDestination(val previousDestination: Fragment? = null) {
    class Home(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Categories(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Upload(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Favorites(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Profile(previousDestination: Fragment) : MainNavDestination(previousDestination)
    object Search : MainNavDestination()
    object Filter : MainNavDestination()
}
