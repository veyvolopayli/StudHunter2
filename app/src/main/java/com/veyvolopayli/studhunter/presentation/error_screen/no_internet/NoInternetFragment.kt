package com.veyvolopayli.studhunter.presentation.error_screen.no_internet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment(R.layout.fragment_no_internet) {

    private var binding: FragmentNoInternetBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNoInternetBinding.bind(view)
        this.binding = binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}