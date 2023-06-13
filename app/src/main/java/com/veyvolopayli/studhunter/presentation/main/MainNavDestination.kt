package com.veyvolopayli.studhunter.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.util.ListResourceBundle

sealed class MainNavDestination(val previousDestination: Fragment? = null, val bundle: Bundle? = null) {
    class Home(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Categories(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Upload(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Favorites(previousDestination: Fragment) : MainNavDestination(previousDestination)
    class Profile(previousDestination: Fragment) : MainNavDestination(previousDestination)
    object Search : MainNavDestination()
    object Filter : MainNavDestination()

    class Publication(previousDestination: Fragment? = null, bundle: Bundle) : MainNavDestination(previousDestination, bundle)
}
