package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.veyvolopayli.studhunter.databinding.FragmentFirstSignUpBinding

class FirstSignUpFragment() : Fragment() {

    private lateinit var binding: FragmentFirstSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstSignUpBinding.inflate(layoutInflater, container, false)

        binding.continueButton.setOnClickListener {

        }

        return binding.root
    }

}