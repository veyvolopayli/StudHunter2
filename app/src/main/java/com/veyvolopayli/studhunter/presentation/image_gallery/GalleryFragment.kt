package com.veyvolopayli.studhunter.presentation.image_gallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.veyvolopayli.studhunter.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private var binding: FragmentGalleryBinding? = null
    private val viewModel: GalleryViewModel by viewModels()

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

        return binding.root
    }

    private fun hasReadStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Explain why the permission is needed if necessary
        }

        requestPermissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun showImageGallery() {
        viewModel.images.observe(viewLifecycleOwner) { uris ->
            val adapter = ImageGalleryAdapter()
            adapter.setData(uris)

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