package com.veyvolopayli.studhunter.common

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.veyvolopayli.studhunter.presentation.categories_screen.CategoriesFragment
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment

object Screens {
//    fun Main() = FragmentScreen { MainFragment() }
//    fun AddressSearch() = FragmentScreen { AddressSearchFragment() }
//    fun Profile(userId: Long) = FragmentScreen("Profile_$userId") { ProfileFragment(userId) }
//    fun Browser(url: String) = ActivityScreen { Intent(Intent.ACTION_VIEW, Uri.parse(url))  }

    fun home() = FragmentScreen { HomeFragment() }
    fun categories() = FragmentScreen { CategoriesFragment() }
}