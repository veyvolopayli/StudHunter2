package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        val vpAdapter = SignUpVPAdapter(requireActivity())
        binding.signUpVp.adapter = vpAdapter
        binding.signUpVp.isUserInputEnabled = false

        binding.backIv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}