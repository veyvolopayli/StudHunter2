package com.veyvolopayli.studhunter.common

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.veyvolopayli.studhunter.R

fun Fragment.replaceFragment(container: Int, newFragment: Fragment, backStack: String?) {
    parentFragmentManager.commit {
        setCustomAnimations(R.anim.slide_in_right, R.anim.no_animation, R.anim.no_animation, R.anim.slide_out_left)
        replace(container, newFragment)
        addToBackStack(backStack)
    }
}

fun Fragment.replaceFragment(container: Int, newFragment: Fragment) {
    parentFragmentManager.commit {
        setCustomAnimations(R.anim.slide_in_right, R.anim.no_animation, R.anim.no_animation, R.anim.slide_out_left)
        replace(container, newFragment)
    }
}

fun Fragment.showFragment(container: Int, currentFragment: Fragment?, newFragment: Fragment, backStack: String?) {
    if (currentFragment == newFragment) return
    parentFragmentManager.commit {
        currentFragment?.let { hide(it) }
        if (!newFragment.isAdded) add(container, newFragment)
        else show(newFragment)
        addToBackStack(backStack)
    }
}

fun Fragment.showFragment(container: Int, currentFragment: Fragment?, newFragment: Fragment) {
    if (currentFragment == newFragment) return
    parentFragmentManager.commit {
        currentFragment?.let { hide(it) }
        if (!newFragment.isAdded) add(container, newFragment)
        else show(newFragment)
    }
}

fun Fragment.removeFragment(container: Int, fragment: Fragment) {
    parentFragmentManager.beginTransaction().remove(fragment).commit()
}

fun AppCompatActivity.replaceFragment(container: Int, newFragment: Fragment, backStack: String?) {
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        setCustomAnimations(R.anim.slide_in_right, R.anim.no_animation, R.anim.no_animation, R.anim.slide_out_left)
        replace(container, newFragment)
        addToBackStack(backStack)
    }
}

fun AppCompatActivity.replaceFragment(container: Int, newFragment: Fragment) {
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        setCustomAnimations(R.anim.slide_in_right, R.anim.no_animation, R.anim.no_animation, R.anim.slide_out_left)
        replace(container, newFragment)
    }
}

fun AppCompatActivity.showFragment(container: Int, currentFragment: Fragment?, newFragment: Fragment, backStack: String?) {
    if (currentFragment === newFragment) return
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        currentFragment?.let { hide(it) }
        if (!newFragment.isAdded) add(container, newFragment)
        else show(newFragment)
        addToBackStack(backStack)
    }
}

fun AppCompatActivity.showFragment(container: Int, currentFragment: Fragment?, newFragment: Fragment) {
    if (currentFragment === newFragment) return
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        currentFragment?.let { hide(it) }
        if (!newFragment.isAdded) add(container, newFragment)
        else show(newFragment)
    }
}

fun AppCompatActivity.removeFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}

fun View.hide() { this.visibility = View.GONE }
fun View.show() { this.visibility = View.VISIBLE }