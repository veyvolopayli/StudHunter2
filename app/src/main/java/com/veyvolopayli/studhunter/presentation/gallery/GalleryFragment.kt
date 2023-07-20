package com.veyvolopayli.studhunter.presentation.gallery

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BottomSheetDialogFragment() {
    private var binding: FragmentGalleryBinding? = null
    private val viewModel: GalleryViewModel by viewModels()
    private var images: List<String>? = null
    private val adapter = ImageGalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        this.binding = binding

        binding.apply.setOnClickListener {
            val images = adapter.selectedImages
            if (images.isNotEmpty()) {
                setFragmentResult("imagesKey", bundleOf("selectedImages" to images))
            }
            dismiss()
        }

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                showImageGallery()
            }
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                showImageGallery()
            }
            else -> {
                if (Build.VERSION.SDK_INT >= 33) {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }

        return binding.root
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showImageGallery()
            } else {
                Toast.makeText(requireContext(), "Пидарас", Toast.LENGTH_SHORT).show()
            }
        }

    private fun showImageGallery() {
        viewModel.images.observe(viewLifecycleOwner) { uris ->
            adapter.setData(uris)

            images = uris

            binding?.imagesRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            binding?.imagesRv?.adapter = adapter

            adapter.onItemClick = {
                adapter.dataChanged()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}