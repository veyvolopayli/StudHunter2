package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SignUpVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

//    var onItemClick : ((String) -> Unit)? = null
    private var fragments = listOf(FirstSignUpFragment(), SecondSignUpFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}
