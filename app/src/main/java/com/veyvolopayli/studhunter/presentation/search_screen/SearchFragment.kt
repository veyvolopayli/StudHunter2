package com.veyvolopayli.studhunter.presentation.search_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)
        this.binding = binding

        binding.mainFragmentSearchSearch.setOnClickListener {
            val query = binding.mainFragmentSearchEt.text.toString().trim()
            if (query.isEmpty()) return@setOnClickListener
            val bundle = bundleOf("query" to query)
            findNavController().navigate(R.id.action_searchFragment_to_searchedPublicationsFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}