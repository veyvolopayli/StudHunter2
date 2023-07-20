package com.veyvolopayli.studhunter.presentation.universities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.databinding.FragmentUniversitiesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversitiesFragment : BottomSheetDialogFragment() {

    private var binding: FragmentUniversitiesBinding? = null
    private val viewModel: UniversitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUniversitiesBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val adapter = UniversitiesAdapter()

        viewModel.universities.observe(viewLifecycleOwner) { universities ->
            adapter.setData(universities)
            adapter.onItemClick = {
                setFragmentResult("university_key", bundleOf("university" to it))
                dismiss()
            }
            binding.universitiesRv.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}