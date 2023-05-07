package com.veyvolopayli.studhunter.presentation.sign_in_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.veyvolopayli.studhunter.databinding.FragmentSignInBinding
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.presentation.MainActivity
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

        binding.button.setOnClickListener {
            val signInRequest = SignInRequest(
                username = binding.username.text.toString(),
                password = binding.password.text.toString()
            )

            vm.signIn(signInRequest)

            vm.state.observe(requireActivity()) {
                if (it.username.isNotEmpty()) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.putExtra("token", it.username)
                    startActivity(intent)
                }
            }
        }

        return binding.root
    }

}