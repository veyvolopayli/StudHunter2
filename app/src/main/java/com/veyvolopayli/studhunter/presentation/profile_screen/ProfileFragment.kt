package com.veyvolopayli.studhunter.presentation.profile_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.parcelable
import com.veyvolopayli.studhunter.databinding.FragmentProfileBinding
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.presentation.edit_profile_screen.EditProfileState
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.util.date.getTimeMillis
import java.security.Signature

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()
    private val mainVm: MainViewModel by activityViewModels()
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        viewModel.user.observe(viewLifecycleOwner) { user ->
            Glide.with(this).load("${Constants.CLOUD_USER_PROFILE_IMAGES_PATH}${user.id}")
                .placeholder(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_user_avatar, requireContext().theme
                    )
                ).circleCrop().signature(ObjectKey(getTimeMillis().toString()))
                .into(binding.profileImageIv)

            val fullName = "${user.name} ${user.surname ?: ""}".trim()
            val username = user.username
            val email = user.email

            binding.fullName.text = fullName
            binding.username.text = username
            binding.email.text = email

            this.user = user
        }

        viewModel.authorized.observe(viewLifecycleOwner) { authorized ->
            if (!authorized) {
                findNavController().navigate(R.id.action_profileFragment_to_auth)
            }
        }

        binding.myPublicationsTv.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_myPublicationsFragment)
        }

        binding.profileEditIv.setOnClickListener {
            val name = binding.fullName.text.split(" ")[0]
            val surname = binding.fullName.text.split(" ").getOrNull(1)
            val state = EditProfileState(
                name = name,
                surname = surname ?: "",
                university = user?.university ?: ""
            )
            val bundle = bundleOf("user" to state)
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment, bundle)
        }

        binding.profileImageIv.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_uploadProfileImageFragment)
        }

        setFragmentResultListener("edited_data") { _, bundle ->
            val updatedData = bundle.parcelable<EditProfileState>("data")
            updatedData?.let { data ->
                viewModel.updateUser(
                    name = data.name,
                    surname = data.surname,
                    university = data.university
                )
            }
        }

        setFragmentResultListener("isAvatarUploaded") { _, bundle ->
            val isUploaded = bundle.getBoolean("uploaded", false)
            if (isUploaded) {
                user?.id?.let {
                    Glide.with(this).load("${Constants.BASE_URL}user/${it}/avatar")
                        .placeholder(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_user_avatar, requireContext().theme
                            )
                        ).circleCrop().signature(ObjectKey(getTimeMillis().toString()))
                        .into(binding.profileImageIv)
                }
            }
        }

        binding.exitAppTv.setOnClickListener {
            viewModel.logout()
            findNavController().setGraph(R.navigation.nav_graph)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainVm.showBottomBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}