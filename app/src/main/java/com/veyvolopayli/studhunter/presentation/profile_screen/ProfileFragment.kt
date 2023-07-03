package com.veyvolopayli.studhunter.presentation.profile_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        viewModel.user.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.loadingLayout.root.show()
                }
                is Resource.Success -> {
                    resource.data?.let { user ->
                        binding.loadingLayout.root.hide()

                        val fullName = "${user.name} ${user.surname ?: ""}".trim()
                        val username = user.username
                        val email = user.email

                        binding.fullName.text = fullName
                        binding.username.text = username
                        binding.email.text = email
                    }

                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "ERROR: ${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}