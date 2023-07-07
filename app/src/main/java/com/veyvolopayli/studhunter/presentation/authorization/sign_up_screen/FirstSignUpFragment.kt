package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentFirstSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstSignUpFragment() : Fragment() {

    private lateinit var binding: FragmentFirstSignUpBinding
    private val vm: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstSignUpBinding.inflate(layoutInflater, container, false)

        vm.signUpState.observe(viewLifecycleOwner) {
            vm.firstPageListener()
        }

        binding.username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.Username(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.Password(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SignUpViewModel.SignUpTextField.Email(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        vm.firstDataIsValid.observe(viewLifecycleOwner) { isValid ->
            binding.continueButton.isEnabled = isValid
        }

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstSignUpFragment_to_secondSignUpFragment)
        }

        return binding.root
    }

}