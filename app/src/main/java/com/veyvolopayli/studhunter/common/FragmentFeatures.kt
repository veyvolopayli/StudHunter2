package com.veyvolopayli.studhunter.common

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.veyvolopayli.studhunter.R

fun Fragment.replaceFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    parentFragmentManager.commit {
        setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out_right)
        replace(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun Fragment.showFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    parentFragmentManager.commit {
        if (newFragment.isAdded && !newFragment.isVisible) show(newFragment)
        if (!newFragment.isAdded) add(container, newFragment)

//        setCustomAnimations(R.anim.slide_in_animation, R.anim.slide_out_animation, R.anim.no_animation, R.anim.slide_out_animation)
//        if (newFragment.isHidden) show(newFragment)
//        else if (!newFragment.isAdded) {
//            add(container, newFragment)
//            if (addToBackStack) addToBackStack(null)
//        }
    }
}

fun Fragment.removeFragment(container: Int, fragment: Fragment) {
    parentFragmentManager.beginTransaction().remove(fragment).commit()
}

fun AppCompatActivity.replaceFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    supportFragmentManager.commit {
        setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left)
        replace(container, newFragment)
        if (addToBackStack) addToBackStack(null)
    }
}

fun AppCompatActivity.showFragment(container: Int, newFragment: Fragment, tag: String, addToBackStack: Boolean) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    supportFragmentManager.commit {
        if (newFragment.isAdded) {
            show(newFragment)
            Log.e(newFragment.toString().substringBefore("@"), "fragment is added")
        }
        else if (!newFragment.isAdded) {
            add(container, newFragment, tag)
            if (addToBackStack) addToBackStack(null)
            Log.e(newFragment.toString().substringBefore("@"), "fragment is not added")
        }
        else {
            Log.e(newFragment.toString().substringBefore("@"), "else")
        }
    }
}

fun AppCompatActivity.showFragment(container: Int, newFragment: Fragment, addToBackStack: Boolean) {
    supportFragmentManager.commit {
        if (newFragment.isAdded) {
            show(newFragment)
            Log.e(newFragment.toString().substringBefore("@"), "fragment is added")
        }
        else if (!newFragment.isAdded) {
            add(container, newFragment)
            if (addToBackStack) addToBackStack(null)
            Log.e(newFragment.toString().substringBefore("@"), "fragment is not added")
        }
        else {
            Log.e(newFragment.toString().substringBefore("@"), "else")
        }
    }
}

fun AppCompatActivity.removeFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}

fun View.hide() { this.visibility = View.GONE }
fun View.show() { this.visibility = View.VISIBLE }