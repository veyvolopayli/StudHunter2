package com.veyvolopayli.studhunter.presentation.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BottomSheetDialogFragment() {
    private var binding: FragmentGalleryBinding? = null
    private val viewModel: GalleryViewModel by viewModels()
    private val isApiUnder33 = Build.VERSION.SDK_INT < 33
    private var images: List<String>? = null

    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            showImageGallery()
        } else {
            // Handle permission denied case
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        this.binding = binding

        if (hasReadStoragePermission()) {
            showImageGallery()
        } else {
            requestStoragePermission()
        }

        binding.apply.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun hasReadStoragePermission(): Boolean {
        return if (isApiUnder33) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Explain why the permission is needed if necessary
        }

        if (isApiUnder33) {
            requestPermissionsLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            requestPermissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun showImageGallery() {
        viewModel.images.observe(viewLifecycleOwner) { uris ->
            val adapter = ImageGalleryAdapter()
            adapter.setData(uris)

            images = uris

            binding?.imagesRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            binding?.imagesRv?.adapter = adapter

            adapter.onItemClick = {
                adapter.dataChanged()
            }

        }

    }

    fun getImages() = images

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}