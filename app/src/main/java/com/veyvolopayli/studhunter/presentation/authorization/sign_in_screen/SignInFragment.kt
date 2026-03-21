package com.veyvolopayli.studhunter.presentation.authorization.sign_in_screen

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
import com.veyvolopayli.studhunter.feature.auth.SignInViewModel
import com.veyvolopayli.studhunter.feature.auth.ui.SignInScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val vm: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            SignInScreen(
                viewModel = vm,
                onSignedIn = {
                    val opts = NavOptions.Builder()
                        .setPopUpTo(R.id.authFragment, true)
                        .build()
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment, null, opts)
                },
                onBack = { parentFragmentManager.popBackStack() },
            )
        }
    }
}
