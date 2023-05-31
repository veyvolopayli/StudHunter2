package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.base.BaseFragment
import com.veyvolopayli.studhunter.databinding.FragmentSecondSignUpBinding
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondSignUpFragment(
    private val necessarySignUpData: NecessarySignUpData
    ) : BaseFragment<FragmentSecondSignUpBinding>(FragmentSecondSignUpBinding::inflate)
{
    private val vm: SecondSignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.cacheNecessaryData(necessarySignUpData)
        val (username, password, email) = necessarySignUpData

        var name = ""
        var surname = ""
        var university = ""

        binding.continueButton.setOnClickListener {
            name = binding.name.toString().trim()
            surname = binding.surname.toString().trim()
            university = binding.university.toString().trim()

//            val signUpRequest = SignUpRequest(
////                username = username, password = password,
//            )
        }
    }
}