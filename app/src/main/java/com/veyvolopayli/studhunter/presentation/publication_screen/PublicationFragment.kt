package com.veyvolopayli.studhunter.presentation.publication_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicationFragment() : Fragment() {

    private var binding: FragmentPublicationBinding? = null
    private val viewModel: PublicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPublicationBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val id = arguments?.getString("id", "") ?: ""

        binding.apply {
            shimmerImage.startShimmer()
            shimmerData.startShimmer()
            shimmerUser.startShimmer()
        }

        viewModel.apply {
            fetchPublication(id)
            fetchImages(id)
        }

        viewModel.imagesState.observe(viewLifecycleOwner) { urls ->
            binding.shimmerImage.apply {
                stopShimmer()
                hide()
            }

            val imagesAdapter = ImagesAdapter()
            imagesAdapter.setImages(urls)
            binding.imagesVp.adapter = imagesAdapter
            binding.imagesVp.show()
        }

        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.shimmerData.apply {
                stopShimmer()
                hide()
            }
            binding.apply {
                state.price?.let {
                    price.text = it.toString()
                    price.show()
                }

                title.text = state.title
                description.text = state.description
                priceType.text = state.priceType
                publicationData.show()
            }
        }

        viewModel.userState.observe(viewLifecycleOwner) { user ->
            binding.shimmerUser.apply {
                stopShimmer()
                hide()
            }
            binding.apply {
                userLayout.show()
                Log.e("User", user.toString())
                userFullName.text = "${user.name} ${user.surname}" ?: ""
                username.text = user.username
                userRating.text = user.rating.toString()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}