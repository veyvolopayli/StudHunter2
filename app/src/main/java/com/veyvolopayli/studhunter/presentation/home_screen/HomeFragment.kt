package com.veyvolopayli.studhunter.presentation.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.veyvolopayli.studhunter.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var publicationsAdapter: HomeRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.state.observe(viewLifecycleOwner) { homeState ->
            val publications = homeState.publications
            publicationsAdapter = HomeRvAdapter(publications)
            binding.rvHome.adapter = publicationsAdapter
        }

        viewModel.event.observe(viewLifecycleOwner) { homeEvent ->
            when (homeEvent) {
                is HomeEvent.Loading -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }
                is HomeEvent.Success -> {
                    binding.loadingLayout.root.visibility = View.GONE
                }
                is HomeEvent.Error -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }
            }
        }

        return binding.root
    }
}