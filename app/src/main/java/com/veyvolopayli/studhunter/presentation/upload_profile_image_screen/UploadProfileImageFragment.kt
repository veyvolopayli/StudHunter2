package com.veyvolopayli.studhunter.presentation.upload_profile_image_screen

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentUploadProfileImageBinding
import com.veyvolopayli.studhunter.presentation.create_publication_screen.CreatePublicationFragment
import com.veyvolopayli.studhunter.presentation.gallery.GalleryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadProfileImageFragment: Fragment(R.layout.fragment_upload_profile_image) {

    private var binding: FragmentUploadProfileImageBinding? = null
    private val vm: UploadProfileImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUploadProfileImageBinding.bind(view)
        this.binding = binding

        val galleryFragment = GalleryFragment()

        binding.image.setOnClickListener {
            galleryFragment.show(parentFragmentManager, null)
        }

        binding.button.setOnClickListener {
            vm.uploadProfileImage()
        }

        vm.loading.observe(viewLifecycleOwner) { loading ->
            binding.loadingLayout.root.visibility = if (loading) View.VISIBLE else View.GONE
        }

        vm.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
            }
            setFragmentResult("isAvatarUploaded", bundleOf("uploaded" to isSuccess))
        }

        vm.chosenImage.observe(viewLifecycleOwner) { uriString ->
            val uri = Uri.parse(uriString)
            binding.image.setImageURI(uri)
        }

        setFragmentResultListener("imagesKey") { _, bundle ->
            val image = bundle.getStringArrayList("selectedImages")?.toList()?.first() ?: run {
                return@setFragmentResultListener
            }
            vm.setChosenImage(image)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}