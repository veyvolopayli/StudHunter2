package com.veyvolopayli.studhunter.presentation.searched_publications_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentSearchedPublicationsBinding
import com.veyvolopayli.studhunter.presentation.home_screen.HomeRvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchedPublicationsFragment(): Fragment(R.layout.fragment_searched_publications) {

    private var binding: FragmentSearchedPublicationsBinding? = null
    private val vm: SearchedPublicationsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchedPublicationsBinding.bind(view)
        this.binding = binding

        val query = arguments?.getString("query")?.trim()

        val adapter = HomeRvAdapter()
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = adapter

        vm.searchedPublications.observe(viewLifecycleOwner) { publications ->
            adapter.setData(publications)
        }

        vm.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        if (query != null) {
            vm.searchPublications(query)
        } else {
            Toast.makeText(requireContext(), "query == null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}