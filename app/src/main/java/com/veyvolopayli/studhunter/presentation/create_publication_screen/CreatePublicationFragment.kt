package com.veyvolopayli.studhunter.presentation.create_publication_screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentCreatePublicationBinding
import com.veyvolopayli.studhunter.presentation.gallery.GalleryFragment
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreatePublicationFragment : Fragment() {

    private var binding: FragmentCreatePublicationBinding? = null
    private val viewModel: CreatePublicationViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCreatePublicationBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val galleryBottomSheet = GalleryFragment()
        galleryBottomSheet.show(parentFragmentManager, null)

        val imagesAdapter = CreatePublicationImagesAdapter()
        imagesAdapter.setData(listOf(TEMP_IMAGE_URL))
        binding.publicationRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.publicationRecycler.adapter = imagesAdapter

        viewModel.priceTypes.observe(viewLifecycleOwner) { types ->
            val values = types.values.toList()
            if (savedInstanceState == null) binding.priceType.setText(values[0])
            val priceTypesAdapter = ArrayAdapter(requireContext(), R.layout.price_type_item, values)
            binding.priceType.setAdapter(priceTypesAdapter)
            binding.priceType.setDropDownBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.background_12px))
        }

        viewModel.selectedImages.observe(viewLifecycleOwner) { images ->
            imagesAdapter.setData(images)
        }

        setFragmentResultListener(IMAGES_KEY) { _, bundle ->
            val images = bundle.getStringArrayList(SELECTED_IMAGES_KEY)?.toList() ?: run {
                return@setFragmentResultListener
            }
            viewModel.setSelectedImages(images)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.showBottomBar()
    }

    private companion object {
        const val TEMP_IMAGE_URL = "http://5.181.255.253/image/common/create_publication_temp_image"
        const val IMAGES_KEY = "imagesKey"
        const val SELECTED_IMAGES_KEY = "selectedImages"
    }
}