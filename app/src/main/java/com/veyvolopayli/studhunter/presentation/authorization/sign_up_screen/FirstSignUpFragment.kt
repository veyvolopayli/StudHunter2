package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.emailIsValid
import com.veyvolopayli.studhunter.common.passwordIsValid
import com.veyvolopayli.studhunter.common.usernameIsValid
import com.veyvolopayli.studhunter.databinding.FragmentFirstSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstSignUpFragment() : Fragment() {

    private var binding: FragmentFirstSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstSignUpBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        binding.username.addTextChangedListener(fSignUpTextWatcher)
        binding.password.addTextChangedListener(fSignUpTextWatcher)
        binding.email.addTextChangedListener(fSignUpTextWatcher)

        return binding.root
    }

    private val fSignUpTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding?.let { bind ->
                val username = bind.username.text.toString().trim()
                val password = bind.password.text.toString().trim()
                val email = bind.email.text.toString().trim()

                if (username.usernameIsValid()) {
                    bind.usernameLayout.isErrorEnabled = false
                }
                if (password.passwordIsValid()) {
                    bind.passwordLayout.isErrorEnabled = false
                }
                if (password.emailIsValid()) {
                    bind.emailLayout.isErrorEnabled = false
                }

                if (username.usernameIsValid() && password.passwordIsValid() && email.emailIsValid()) {
                    bind.continueButton.apply {
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary))
                        setOnClickListener {
                            val bundle = bundleOf("username" to username, "password" to password, "email" to email)
//                            val secondSignUpFragment = SecondSignUpFragment()
//                            secondSignUpFragment.arguments = bundle
//                            parentFragmentManager.commit {
//                                setCustomAnimations(R.anim.slide_in_left, R.anim.no_animation, R.anim.no_animation, R.anim.slide_out_left)
//                                replace(R.id.sign_up_fragment_container, secondSignUpFragment)
//                                addToBackStack(null)
//                            }
                        }
                    }
                } else {
                    bind.continueButton.apply {
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.secondary))
                        setOnClickListener {
                            if (!username.usernameIsValid()) {
                                bind.usernameLayout.error = "Некорректное имя пользователя"
                            }
                            if (!password.passwordIsValid()) {
                                bind.passwordLayout.error = "Некорректный пароль"
                            }
                            if (!email.emailIsValid()) {
                                bind.emailLayout.error = "Некорректное мыло"
                            }
                        }
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

}