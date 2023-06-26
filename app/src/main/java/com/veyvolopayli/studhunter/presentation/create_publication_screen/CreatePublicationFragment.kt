package com.veyvolopayli.studhunter.presentation.create_publication_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.databinding.FragmentCreatePublicationBinding
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.presentation.gallery.GalleryFragment
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

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

        var userId: String? = null

        viewModel.userId.observe(viewLifecycleOwner) {
            userId = it
        }

        val galleryBottomSheet = GalleryFragment()

        val imagesAdapter = CreatePublicationImagesAdapter()
        imagesAdapter.setData(listOf(TEMP_IMAGE_URL))
        binding.publicationRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.publicationRecycler.adapter = imagesAdapter

        imagesAdapter.onItemClick = {
            galleryBottomSheet.show(parentFragmentManager, null)
        }

        binding.publishButton.setOnClickListener {
            if (userId == null) {
                Toast.makeText(requireContext(), "user id is null", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val publicationImages = imagesAdapter.getSelectedImages()

            val publicationToUpload = PublicationToUpload(
                title = binding.title.string() ?: kotlin.run { return@setOnClickListener },
                description = binding.description.string() ?: kotlin.run { return@setOnClickListener },
                district = "Строгино",
                price = binding.price.int(),
                priceType = binding.priceType.text.toString().trim(),
                category = "негры",
                userId = userId!!,
                socials = "главный негр"
            )
            viewModel.uploadPublication(images = publicationImages, publicationToUpload = publicationToUpload)
        }

        viewModel.priceTypes.observe(viewLifecycleOwner) { types ->
            val values = types.values.toList()
            if (savedInstanceState == null) binding.priceType.setText(values[0])
            val priceTypesAdapter = ArrayAdapter(requireContext(), R.layout.price_type_item, values)
            binding.priceType.setAdapter(priceTypesAdapter)
            binding.priceType.setDropDownBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_12px
                )
            )
        }

        viewModel.selectedImages.observe(viewLifecycleOwner) { images ->
            imagesAdapter.setData(images)
        }

        viewModel.state.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.loadingLayout.root.visibility = View.GONE
                    Toast.makeText(requireContext(), resource.data, Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }

                is Resource.Error -> {
                    binding.loadingLayout.root.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "ERROR: ${resource.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        setFragmentResultListener(IMAGES_KEY) { _, bundle ->
            val images = bundle.getStringArrayList(SELECTED_IMAGES_KEY)?.toList() ?: run {
                return@setFragmentResultListener
            }
            viewModel.setSelectedImages(images)
        }

        return binding.root
    }

    private fun TextInputEditText.string(): String? {
        val text = text.toString().trim()
        return text.ifEmpty { null }
    }

    private fun TextInputEditText.int(): Int? {
        return try {
            text.toString().trim().toIntOrNull()
        } catch (e: Exception) {
            null
        }
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