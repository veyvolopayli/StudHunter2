package com.veyvolopayli.studhunter.presentation.sign_in_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.common.AuthorizationResult
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.fragments.removeFragment
import com.veyvolopayli.studhunter.common.fragments.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentSignInBinding
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val vm: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)

        vm.signInResult.observe(viewLifecycleOwner) { signInResult ->
            when (signInResult) {
                is AuthorizationResult.Authorized -> {
                    loadingLayoutVisibility(false, binding.loadingLayout.root)
                    replaceFragment(R.id.main_fragment_container, HomeFragment(), false)
                    removeFragment(R.id.main_fragment_container, this)
                }
                is AuthorizationResult.Error -> {
                    when (signInResult.error) {
                        is ErrorType.ServerError -> {

                        }
                        is ErrorType.NetworkError -> {

                        }
                        is ErrorType.UnexpectedError -> {
                            toast(getString(R.string.unknown_error))
                        }
                        else -> {

                        }
                    }
                    loadingLayoutVisibility(false, binding.loadingLayout.root)
                }
                is AuthorizationResult.WrongData -> {
                    loadingLayoutVisibility(false, binding.loadingLayout.root)
                    toast(getString(R.string.wrong_username_or_password))
                }
                is AuthorizationResult.UnknownError -> {

                }
            }
        }

        binding.button.setOnClickListener {
            val signInRequest = SignInRequest(
                username = binding.username.text.toString().trim(),
                password = binding.password.text.toString().trim()
            )

            vm.signIn(signInRequest)
        }

        return binding.root
    }

    private fun loadingLayoutVisibility(loading: Boolean, view: FrameLayout) {
        view.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}