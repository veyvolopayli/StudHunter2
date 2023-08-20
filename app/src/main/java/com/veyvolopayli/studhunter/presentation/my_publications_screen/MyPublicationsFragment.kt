package com.veyvolopayli.studhunter.presentation.my_publications_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.FragmentMyPublicationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPublicationsFragment : Fragment(R.layout.fragment_my_publications) {

    private var binding: FragmentMyPublicationsBinding? = null
    private val viewModel: MyPublicationsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMyPublicationsBinding.bind(view)
        this.binding = binding

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.shimmerLayout.startShimmer()

        val myPublicationsAdapter = MyPublicationsAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myPublicationsAdapter
        }

        viewModel.myPublications.observe(viewLifecycleOwner) { myPublications ->
            myPublicationsAdapter.setPublications(myPublications)
            binding.shimmerLayout.apply {
                stopShimmer()
                hide()
            }
            binding.rv.show()
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}