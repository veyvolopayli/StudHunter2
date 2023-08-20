package com.veyvolopayli.studhunter.presentation.publication_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicationFragment() : Fragment() {
    private var binding: FragmentPublicationBinding? = null
    private val viewModel: PublicationViewModel by viewModels()

    private var thisPubID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPublicationBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val id = arguments?.getString("id", "") ?: ""
        thisPubID = id

        viewModel.checkFavorite(id)

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

        viewModel.userIsOwnerState.observe(viewLifecycleOwner) { isOwner ->
            binding.vpWriteButton.visibility = if (isOwner) View.GONE else View.VISIBLE
        }

        viewModel.favorite.observe(viewLifecycleOwner) {
            binding.addToFavoriteButton.setChecked(it)
        }

        binding.addToFavoriteButton.setOnClickListener {
            viewModel.onFavoriteClick(id)
        }

        binding.vpWriteButton.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("pub_id", id)

            findNavController().navigate(R.id.action_publicationFragment_to_userChatFragment, bundle)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}