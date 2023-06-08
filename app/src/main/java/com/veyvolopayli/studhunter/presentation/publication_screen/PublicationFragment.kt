package com.veyvolopayli.studhunter.presentation.publication_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicationFragment(private val id: String) : Fragment() {

    private var binding: FragmentPublicationBinding? = null
    private val viewModel: PublicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPublicationBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        viewModel.fetchPublication(id)

        viewModel.globalEvent.observe(viewLifecycleOwner) {

        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.title.text = state.title
            binding.description.text = state.description
            binding.price.text = "${state.price} ${state.priceType}"
            binding.price.text = "${state.price} ${state.priceType}"
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}