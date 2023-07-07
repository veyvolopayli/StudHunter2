package com.veyvolopayli.studhunter.presentation.authorization.sign_in_screen

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.base.BaseFragment
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.databinding.FragmentSignInBinding
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {

    private val vm: SignInViewModel by viewModels()
    private val mainVm: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.signInResult.observe(viewLifecycleOwner) { signInResult ->
            when (signInResult) {
                is AuthorizationResult.Authorized -> {
                    loadingLayoutVisibility(false, binding.loadingLayout.root)
                    mainVm.launchAppOk()
                    findNavController().setGraph(R.navigation.nav_graph)
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

        binding.signInFragmentBackIv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun loadingLayoutVisibility(loading: Boolean, view: FrameLayout) {
        view.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}