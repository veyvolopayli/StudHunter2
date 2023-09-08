package com.veyvolopayli.studhunter.presentation.publication_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.Constants
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

        val publicationID = arguments?.getString("id", "") ?: ""

        binding.shimmerLoadingLayout.startShimmer()
        viewModel.getData(publicationID)

        viewModel.imagesState.observe(viewLifecycleOwner) { images ->
            val imagesAdapter = ImagesAdapter()
            imagesAdapter.setImages(images)
            binding.imagesVp.adapter = imagesAdapter
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding) {
                state.price?.let {
                    price.apply {
                        visibility = View.VISIBLE
                        text = it.toString()
                    }
                }
                priceType.text = state.priceType
                title.text = state.title
                description.text = state.description
                userFullName.text = state.userFullName
                userRating.text = state.userRating.toString()
                username.text = state.username
                vpWriteButton.visibility = if (state.isUserOwner) View.GONE else View.VISIBLE
                Glide.with(this@PublicationFragment).load(Constants.getUserAvatarUrl(state.userId)).placeholder(ResourcesCompat.getDrawable(resources, R.drawable.ic_user_avatar, requireContext().theme)).circleCrop().into(avatar)

                if (state.isLoading) {
                    binding.shimmerLoadingLayout.startShimmer()
                    binding.shimmerLoadingLayout.visibility = View.VISIBLE
                    binding.nestedScrollView2.visibility = View.GONE
                } else {
                    binding.shimmerLoadingLayout.stopShimmer()
                    binding.shimmerLoadingLayout.visibility = View.GONE
                    binding.nestedScrollView2.visibility = View.VISIBLE
                }
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.addToFavoriteButton.setChecked(isFavorite)
        }

        binding.addToFavoriteButton.setOnClickListener {
            viewModel.changeFavorite()
        }

        binding.vpWriteButton.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("pub_id", publicationID)

            findNavController().navigate(R.id.action_publicationFragment_to_userChatFragment, bundle)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}