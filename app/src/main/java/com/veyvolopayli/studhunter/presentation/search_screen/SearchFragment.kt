package com.veyvolopayli.studhunter.presentation.search_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)
        this.binding = binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}