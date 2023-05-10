package com.veyvolopayli.studhunter.presentation.start_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val navController = Navigation.findNavController(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            navController.navigate(R.id.action_start_to_sign_in_fragment)
        }

        return binding.root
    }

}