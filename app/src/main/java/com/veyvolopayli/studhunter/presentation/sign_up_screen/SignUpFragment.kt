package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        /*viewPager = binding.signUpViewPager
        val vpAdapter = SignUpViewPagerAdapter(requireActivity(), viewPager)

        viewPager.adapter = vpAdapter*/



        return binding.root
    }

}