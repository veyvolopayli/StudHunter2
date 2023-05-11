package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentFirstSignUpBinding
import com.veyvolopayli.studhunter.presentation.MainActivity

class FirstSignUpFragment(private val viewPager2: ViewPager2) : Fragment() {

    private lateinit var binding: FragmentFirstSignUpBinding
//    private var viewPager: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstSignUpBinding.inflate(layoutInflater, container, false)

        binding.continueButton.setOnClickListener {
            viewPager2.currentItem = 1
        }

        return binding.root
    }

}