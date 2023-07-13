package com.veyvolopayli.studhunter.presentation.auth_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_signInFragment)
        }

        return binding.root
    }

}