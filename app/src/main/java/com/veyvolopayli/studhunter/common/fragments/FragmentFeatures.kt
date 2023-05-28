package com.veyvolopayli.studhunter.common.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.veyvolopayli.studhunter.R

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

fun AppCompatActivity.replaceFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    supportFragmentManager.commit {
        replace(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun AppCompatActivity.showFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    supportFragmentManager.commit {
        if (newFragment.isAdded) show(newFragment)
        else add(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun AppCompatActivity.removeFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}