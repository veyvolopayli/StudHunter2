package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.feature.auth.SignUpViewModel
import com.veyvolopayli.studhunter.feature.auth.ui.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val vm: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            SignUpScreen(
                viewModel = vm,
                onSignedUp = {
                    val opts = NavOptions.Builder()
                        .setPopUpTo(R.id.authFragment, true)
                        .build()
                    findNavController().navigate(R.id.action_signUpFragment_to_mainFragment, null, opts)
                },
                onBack = { parentFragmentManager.popBackStack() },
            )
        }
    }
}
