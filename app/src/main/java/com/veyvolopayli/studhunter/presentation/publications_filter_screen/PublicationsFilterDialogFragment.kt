package com.veyvolopayli.studhunter.presentation.publications_filter_screen

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentPublicationsFilterDialogBinding
import com.veyvolopayli.studhunter.domain.model.FilterRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicationsFilterDialogFragment : BottomSheetDialogFragment(R.layout.fragment_publications_filter_dialog) {

    private var binding: FragmentPublicationsFilterDialogBinding? = null
    private val vm: PublicationsFilterViewModel by viewModels()
    private val categoriesListAdapter = CategoriesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPublicationsFilterDialogBinding.bind(view)
        this.binding = binding

        vm.categories.observe(viewLifecycleOwner) { categories ->
            categoriesListAdapter.setData(categories)
            binding.categoriesRv.apply {
                layoutManager = FlexboxLayoutManager(requireContext())
                adapter = categoriesListAdapter
            }
        }

        vm.toast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.applyButton.onClick = {
            Log.e("G", "CLICKED")
            val minPriceText = binding.minPrice.text
            val maxPriceText = binding.maxPrice.text

            val minPrice = if (minPriceText.isNullOrEmpty()) null else minPriceText.toString().toInt()
            val maxPrice = if (maxPriceText.isNullOrEmpty()) null else maxPriceText.toString().toInt()
            val minUserRating = binding.ratingLayout.chosenRating
            val districts = null
            val categories = categoriesListAdapter.chosenCategories
            val priceTypes = null

            val filterRequest = FilterRequest(
                minPrice = minPrice,
                maxPrice = maxPrice,
                minUserRating = minUserRating,
                districts = districts,
                categories = categories,
                priceTypes = priceTypes
            )

            setFragmentResult("FILTER", bundleOf("filterRequest" to filterRequest))
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}