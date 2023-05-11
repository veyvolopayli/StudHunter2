package com.veyvolopayli.studhunter.presentation.sign_up_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class SignUpViewPagerAdapter(activity: FragmentActivity, viewPager: ViewPager2): FragmentStateAdapter(activity) {

    private val fragments = listOf(FirstSignUpFragment(viewPager), SecondSignUpFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}