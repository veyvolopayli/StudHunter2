package com.veyvolopayli.studhunter.presentation.districts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.databinding.FragmentDistrictsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DistrictsFragment : BottomSheetDialogFragment() {

    private var binding: FragmentDistrictsBinding? = null
    private val viewModel: DistrictsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDistrictsBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val adapter = DistrictsAdapter()

        viewModel.districts.observe(viewLifecycleOwner) { districtsList ->
            adapter.setData(districtsList)
            binding.districtsRv.layoutManager = LinearLayoutManager(requireContext())
            binding.districtsRv.adapter = adapter
            adapter.onItemClick = { district ->
                setFragmentResult("districtKey", bundleOf("district" to district))
                dismiss()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }



}