package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.base.BaseFragment
import com.veyvolopayli.studhunter.common.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentSecondSignUpBinding
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondSignUpFragment()
    : BaseFragment<FragmentSecondSignUpBinding>(FragmentSecondSignUpBinding::inflate)
{
    private val mainVm: MainViewModel by activityViewModels()
    private val vm: SIgnUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.name.setText("Илья")
//        binding.surname.setText("Поло")
//        binding.university.setText("ГУУ")

        vm.secondDataIsValid.observe(viewLifecycleOwner) { isValid ->
            binding.continueButton.isEnabled = isValid
        }

        vm.signUpState.observe(viewLifecycleOwner) {
            vm.secondPageListener()
        }

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SIgnUpViewModel.SignUpTextField.Name(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.surname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SIgnUpViewModel.SignUpTextField.Surname(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.university.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.textChanged(SIgnUpViewModel.SignUpTextField.University(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        vm.signUpResult.observe(viewLifecycleOwner) { authorizationResult ->
            when (authorizationResult) {
                is AuthorizationResult.Authorized -> {
                    replaceFragment(R.id.main_fragment_container, HomeFragment(), false)
                    mainVm.launchAppOk()
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