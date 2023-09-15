package com.veyvolopayli.studhunter.presentation.publications_filter_screen

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.github.javafaker.Faker
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentPublicationsFilterDialogBinding
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

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.e("CATEGORIES", categoriesListAdapter.chosenCategories.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}