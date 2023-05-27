package com.veyvolopayli.studhunter.presentation.auth_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.fragments.removeFragment
import com.veyvolopayli.studhunter.common.fragments.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentAuthBinding
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.sign_in_screen.SignInFragment
import com.veyvolopayli.studhunter.presentation.sign_up_screen.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val vm: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)

        vm.authResult.observe(viewLifecycleOwner) { authResult ->
            if (authResult is AuthResult.Authorized) {
                replaceFragment(
                    container = R.id.main_fragment_container,
                    newFragment = HomeFragment(),
                    addToBackStack = false
                )
                removeFragment(R.id.fullscreen_main_fragment_container, this)
            }
            if (authResult is AuthResult.UnknownError) {
                Toast.makeText(requireContext(), "Unknown error", Toast.LENGTH_SHORT).show()
            }
//            else Toast.makeText(requireContext(), "Something went super wrong! АЛЯРМ", Toast.LENGTH_SHORT).show()
        }

        /*binding.signUpButton.setOnClickListener {
            replaceFragment(
                container = R.id.main_fragment_container,
                newFragment = SignUpFragment(),
                addToBackStack = true
            )
        }*/

        binding.signInButton.setOnClickListener {
            replaceFragment(
                container = R.id.main_fragment_container,
                newFragment = SignInFragment(),
                addToBackStack = true
            )
        }

        return binding.root
    }

}