package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.fragments.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentFirstSignUpBinding

class FirstSignUpFragment() : Fragment() {

    private lateinit var binding: FragmentFirstSignUpBinding
    private var secondSignUpFragment = SecondSignUpFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstSignUpBinding.inflate(layoutInflater, container, false)

        binding.continueButton.setOnClickListener {
            replaceFragment(R.id.sign_up_fragment_container, secondSignUpFragment, true)
        }

        return binding.root
    }

}