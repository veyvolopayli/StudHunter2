package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.veyvolopayli.studhunter.base.BaseFragment
import com.veyvolopayli.studhunter.databinding.FragmentSecondSignUpBinding
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondSignUpFragment()
    : BaseFragment<FragmentSecondSignUpBinding>(FragmentSecondSignUpBinding::inflate)
{
    private val vm: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.Name(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.surname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.Surname(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.university.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.University(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        vm.signUpResult.observe(viewLifecycleOwner) { authorizationResult ->
            when (authorizationResult) {
                is AuthorizationResult.Authorized -> {
//                    mainVm.launchAppOk()
                }
                is AuthorizationResult.WrongData -> {
                    Toast.makeText(requireContext(), "WrongData", Toast.LENGTH_SHORT).show()
                }
                is AuthorizationResult.UnknownError -> {
                    Toast.makeText(requireContext(), authorizationResult.error.toString(), Toast.LENGTH_SHORT).show()
                }
                is AuthorizationResult.Error -> {
                    Toast.makeText(requireContext(), authorizationResult.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.continueButton.setOnClickListener {
            vm.signUp()
        }
    }
}