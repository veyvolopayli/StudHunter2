package com.veyvolopayli.studhunter.presentation.categories_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var binding: FragmentCategoriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}