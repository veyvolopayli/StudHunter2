package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSecondSignUpBinding

class SecondSignUpFragment : Fragment() {

    private lateinit var binding: FragmentSecondSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSecondSignUpBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}