package com.veyvolopayli.studhunter.presentation.auth_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentAuthBinding
import com.veyvolopayli.studhunter.presentation.authorization.sign_in_screen.SignInFragment
import com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen.SignUpFragment

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)

        binding.signUpButton.setOnClickListener {
            replaceFragment(
                container = R.id.main_fragment_container,
                newFragment = SignUpFragment()
            )
        }

        binding.signInButton.setOnClickListener {
            replaceFragment(
                container = R.id.main_fragment_container,
                newFragment = SignInFragment()
            )
        }

        return binding.root
    }

}