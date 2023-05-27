package com.veyvolopayli.studhunter.common.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.veyvolopayli.studhunter.R

//class FragmentFeatures(private val fragmentManager: FragmentManager) {
//    fun replace(fragment: Fragment, addToBackStack: Boolean) {
//        fragmentManager.commit {
//            replace(R.id.fullscreen_main_fragment_container, fragment)
//            if (addToBackStack) addToBackStack(null)
//        }
//    }
//
//    fun show(fragment: Fragment, addToBackStack: Boolean) {
//        fragmentManager.commit {
//            if (fragment.isAdded) show(fragment)
//            else add(R.id.main_fragment_container, fragment)
//            if (addToBackStack) addToBackStack(null)
//        }
//    }
//
//}

fun Fragment.replaceFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    parentFragmentManager.commit {
        replace(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun Fragment.showFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    parentFragmentManager.commit {
        if (newFragment.isAdded) show(newFragment)
        else add(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun Fragment.removeFragment(container: Int, fragment: Fragment) {
    parentFragmentManager.beginTransaction().remove(fragment).commit()
}