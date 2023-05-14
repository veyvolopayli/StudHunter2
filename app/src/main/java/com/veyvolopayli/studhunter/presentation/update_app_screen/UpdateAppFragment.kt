package com.veyvolopayli.studhunter.presentation.update_app_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentUpdateAppBinding

class UpdateAppFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUpdateAppBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}