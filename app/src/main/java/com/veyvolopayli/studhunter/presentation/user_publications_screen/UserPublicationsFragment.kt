package com.veyvolopayli.studhunter.presentation.user_publications_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentUserPublicationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPublicationsFragment: Fragment(R.layout.fragment_user_publications) {
    private var binding: FragmentUserPublicationsBinding? = null
    private val viewModel: UserPublicationsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserPublicationsBinding.bind(view)
        this.binding = binding

        val publicationsAdapter = UserPublicationsAdapter()
        binding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = publicationsAdapter
        }

        val userID = arguments?.getString("userID", null) ?: ""
        viewModel.getUserPublications(userID)

        viewModel.userPublications.observe(viewLifecycleOwner) { publications ->
            publicationsAdapter.setPublications(publications)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}