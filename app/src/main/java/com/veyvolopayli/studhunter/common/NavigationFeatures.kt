package com.veyvolopayli.studhunter.common

import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment

fun FragmentContainerView.obtainNavHostFragment(@NavigationRes navigationGraph: Int): NavHostFragment {
    (getFragment() as? NavHostFragment)?.let { return it }
    return NavHostFragment.create(navigationGraph)
}