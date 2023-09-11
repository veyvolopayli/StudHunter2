package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.emailIsValid
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.nameOrSurnameIsValid
import com.veyvolopayli.studhunter.common.passwordIsValid
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.common.usernameIsValid
import com.veyvolopayli.studhunter.databinding.FragmentSignUpBinding
import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import com.veyvolopayli.studhunter.presentation.universities.UniversitiesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null
    private val vm: SignUpViewModel by viewModels()
    private val mainVm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        mainVm.hideBottomBar()

        binding.username.addTextChangedListener(signUpTextWatcher)
        binding.password.addTextChangedListener(signUpTextWatcher)
        binding.email.addTextChangedListener(signUpTextWatcher)
        binding.name.addTextChangedListener(signUpTextWatcher)
        binding.surname.addTextChangedListener(signUpTextWatcher)
        binding.university.addTextChangedListener(signUpTextWatcher)

        binding.backIv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        vm.state.observe(viewLifecycleOwner) { state ->
            binding.loadingLayout.root.apply {
                if (state.isLoading) show() else hide()
            }
            if (state.isEmailUnique == true && state.isUsernameUnique == true) {
                vm.signUp()
            }
            else if (state.isUsernameUnique == false) {
                binding.usernameLayout.error = getString(R.string.this_username_is_already_taken)
            }
            else if (state.isEmailUnique == false) {
                binding.emailLayout.error = getString(R.string.this_email_is_already_in_use)
            }
        }

        vm.signUpResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AuthorizationResult.Authorized -> {
                    findNavController().setGraph(R.navigation.nav_graph)
                }
                is AuthorizationResult.Error -> {
                    Toast.makeText(requireContext(), result.error.toString().trim(), Toast.LENGTH_SHORT).show()
                }
                is AuthorizationResult.UnknownError -> {
                    Toast.makeText(requireContext(), result.error.toString().trim(), Toast.LENGTH_SHORT).show()
                }
                is AuthorizationResult.WrongData -> Unit
            }
        }

        binding.university.setOnClickListener {
            val universitiesFragment = UniversitiesFragment()
            universitiesFragment.show(parentFragmentManager, null)
        }

        setFragmentResultListener("university_key") { _, bundle ->
            val university = bundle.getString("university")
            binding.university.setText(university)
        }

        return binding.root
    }

    private val signUpTextWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding?.let { bind ->
                val username = bind.username.text.toString().trim()
                val password = bind.password.text.toString().trim()
                val email = bind.email.text.toString().trim()
                val name = bind.name.text.toString().trim()
                val surname = bind.surname.text.toString().trim()
                val university = bind.university.text.toString().trim()

                val usernameIsValid = username.usernameIsValid()
                val passwordIsValid = password.passwordIsValid()
                val emailIsValid = email.emailIsValid()
                val nameIsValid = name.nameOrSurnameIsValid()
                val surnameIsValid = surname.nameOrSurnameIsValid()
                val universityIsValid = university.nameOrSurnameIsValid()

                if (usernameIsValid) {
                    bind.usernameLayout.isErrorEnabled = false
                }
                if (passwordIsValid) {
                    bind.passwordLayout.isErrorEnabled = false
                }
                if (emailIsValid) {
                    bind.emailLayout.isErrorEnabled = false
                }

                if (nameIsValid) {
                    bind.nameLayout.isErrorEnabled = false
                }
                if (surnameIsValid) {
                    bind.surnameLayout.isErrorEnabled = false
                }
                if (universityIsValid) {
                    bind.universityLayout.isErrorEnabled = false
                }

                if (usernameIsValid && passwordIsValid && emailIsValid
                    && nameIsValid && surnameIsValid && universityIsValid) {
                    bind.signUpButton.apply {
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary))
                        setOnClickListener {
                            vm.checkUniqueness(
                                username = username,
                                password = password,
                                email = email,
                                name = name,
                                surname = surname,
                                university = university)
                        }
                    }
                } else {
                    bind.signUpButton.apply {
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.secondary))
                        setOnClickListener {
                            if (!usernameIsValid) {
                                bind.usernameLayout.error = context.getString(R.string.incorrect_username)
                            }
                            if (!passwordIsValid) {
                                bind.passwordLayout.error = context.getString(R.string.incorrect_password)
                            }
                            if (!emailIsValid) {
                                bind.emailLayout.error = context.getString(R.string.incorrect_email)
                            }
                            if (!nameIsValid) {
                                bind.nameLayout.error = context.getString(R.string.strange_name)
                            }
                            if (!surnameIsValid) {
                                bind.surnameLayout.error = context.getString(R.string.strange_surname)
                            }
                            if (!universityIsValid) {
                                bind.universityLayout.error = context.getString(R.string.this_university_does_not_exist)
                            }
                        }
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    }
}