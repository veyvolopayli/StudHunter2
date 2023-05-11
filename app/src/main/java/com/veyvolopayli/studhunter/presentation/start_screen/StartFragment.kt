package com.veyvolopayli.studhunter.presentation.start_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentStartBinding
import com.veyvolopayli.studhunter.presentation.sign_in_screen.SignInFragment
import com.veyvolopayli.studhunter.presentation.sign_up_screen.SignUpFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val signInFragment = SignInFragment()
    private val signUpFragment = SignUpFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        val activity = requireActivity()

        binding.signUpButton.setOnClickListener {
            activity.supportFragmentManager.commit {
                addToBackStack(null)
                add(R.id.main_fragment_container, signUpFragment)
            }
        }

        binding.signInButton.setOnClickListener {
            activity.supportFragmentManager.commit {
                addToBackStack(null)
                add(R.id.main_fragment_container, signInFragment)
            }
        }

        return binding.root
    }

}