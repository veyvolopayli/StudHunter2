package com.veyvolopayli.studhunter.presentation.create_publication_screen

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.veyvolopayli.studhunter.common.categoryIsValid
import com.veyvolopayli.studhunter.common.descriptionIsValid
import com.veyvolopayli.studhunter.common.districtIsValid
import com.veyvolopayli.studhunter.common.priceIsValid
import com.veyvolopayli.studhunter.common.priceTypeIsValid
import com.veyvolopayli.studhunter.common.titleIsValid
import com.veyvolopayli.studhunter.databinding.FragmentCreatePublicationBinding
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.presentation.districts.DistrictsFragment
import com.veyvolopayli.studhunter.presentation.gallery.GalleryFragment
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePublicationFragment : Fragment() {

    private var binding: FragmentCreatePublicationBinding? = null
    private val viewModel: CreatePublicationViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var publicationImages: List<String>? = null

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
        val districtsFragment = DistrictsFragment()

        val imagesAdapter = CreatePublicationImagesAdapter()
        imagesAdapter.setData(listOf(TEMP_IMAGE_URL))
        binding.publicationRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.publicationRecycler.adapter = imagesAdapter

        binding.district.setOnClickListener {
            districtsFragment.show(parentFragmentManager, null)
        }

        imagesAdapter.onItemClick = {
            galleryBottomSheet.show(parentFragmentManager, null)
        }

        with(binding) {
            title.addTextChangedListener(textWatcher)
            description.addTextChangedListener(textWatcher)
            district.addTextChangedListener(textWatcher)
            price.addTextChangedListener(textWatcher)
            priceType.addTextChangedListener(textWatcher)
            category.addTextChangedListener(textWatcher)

            publishButton.setOnClickListener {
                if (userId == null) {
                    Toast.makeText(requireContext(), "user id is null", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                publicationImages = imagesAdapter.getSelectedImages()

                val title = binding.title.text.toString().trim()
                val description = binding.description.text.toString().trim()
                val district = binding.district.text.toString().trim()
                val price = binding.price.text.toString().trim().toIntOrNull() ?: 0
                val priceType = binding.priceType.text.toString().trim()
                val category = binding.category.text.toString().trim()
                val socials = "telegram_username"

                val publicationToUpload = PublicationToUpload(
                    title = title,
                    description = description,
                    district = district,
                    price = price,
                    priceType = priceType,
                    category = category,
                    userId = userId!!,
                    socials = socials
                )
                viewModel.uploadPublication(
                    images = publicationImages ?: emptyList(),
                    publicationToUpload = publicationToUpload
                )
            }
        }

        viewModel.priceTypes.observe(viewLifecycleOwner) { types ->
            val values = types.values.toList()
            if (savedInstanceState == null) binding.priceType.setText(values.first())
            val priceTypesAdapter =
                ArrayAdapter(requireContext(), R.layout.item_autocomplete_textview_default, values)
            binding.priceType.setAdapter(priceTypesAdapter)
            binding.priceType.setDropDownBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_12px
                )
            )
        }

        viewModel.categories.observe(viewLifecycleOwner) { categoriesMap ->
            val categories = categoriesMap.values.toList()
            if (savedInstanceState == null) binding.category.setText(categories.first())
            val categoriesAdapter = ArrayAdapter(
                requireContext(),
                R.layout.item_autocomplete_textview_default,
                categories
            )
            binding.category.setAdapter(categoriesAdapter)
            binding.category.setDropDownBackgroundDrawable(
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

        viewModel.selectedDistrict.observe(viewLifecycleOwner) { district ->
            binding.district.setText(district)
        }

        setFragmentResultListener(IMAGES_KEY) { _, bundle ->
            val images = bundle.getStringArrayList(SELECTED_IMAGES_KEY)?.toList() ?: run {
                return@setFragmentResultListener
            }
            viewModel.setSelectedImages(images)
        }

        setFragmentResultListener(DISTRICTS_KEY) { _, bundle ->
            val selectedDistrict = bundle.getString("district", null) ?: run {
                return@setFragmentResultListener
            }
            viewModel.setSelectedDistrict(selectedDistrict)
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
        const val DISTRICTS_KEY = "districtKey"
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding?.let {
                with(it) {
                    val title = title.text.toString().trim()
                    val description = description.text.toString().trim()
                    val district = district.text.toString().trim()
                    val price = price.text.toString().trim().toIntOrNull() ?: 0
                    val priceType = priceType.text.toString().trim()
                    val category = category.text.toString().trim()

                    if (title.titleIsValid() && description.descriptionIsValid()
                        && district.districtIsValid() && price.priceIsValid()
                        && priceType.priceTypeIsValid() && category.categoryIsValid()
                    ) {
                        publishButton.backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(requireContext(), R.color.primary)
                            )
                        publishButton.isEnabled = true
                    } else {
                        publishButton.backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(requireContext(), R.color.tertiary)
                            )
                        publishButton.isEnabled = false
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
}