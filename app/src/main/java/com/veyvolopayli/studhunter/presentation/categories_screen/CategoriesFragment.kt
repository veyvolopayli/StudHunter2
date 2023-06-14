package com.veyvolopayli.studhunter.presentation.categories_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var binding: FragmentCategoriesBinding? = null
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        viewModel.categoriesState.observe(viewLifecycleOwner) { categories ->
            val categoriesAdapter = CategoriesRvAdapter()
            categoriesAdapter.setData(categories)
            categoriesAdapter.onItemClick = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            binding.categoriesRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = categoriesAdapter
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}