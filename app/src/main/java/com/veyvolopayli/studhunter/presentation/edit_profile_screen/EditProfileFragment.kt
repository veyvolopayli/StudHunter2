package com.veyvolopayli.studhunter.presentation.edit_profile_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.parcelable
import com.veyvolopayli.studhunter.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private var binding: FragmentEditProfileBinding? = null
    private val vm: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditProfileBinding.bind(view)
        this.binding = binding

        val passedUser = arguments?.parcelable<EditProfileState>("user")

        passedUser?.let { user ->
            binding.name.setText(user.name)
            binding.surname.setText(user.surname)
            binding.university.setText(user.university)
        }

        vm.result.observe(viewLifecycleOwner) { state ->
            setFragmentResult("edited_data", bundleOf("data" to state))
            findNavController().navigateUp()
        }

        vm.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        vm.loading.observe(viewLifecycleOwner) { loading ->
            binding.loadingLayout.root.visibility = if (loading) View.VISIBLE else View.GONE
        }

        binding.button.setOnClickListener {
            with(binding) {
                vm.editProfile(
                    name = name.text.toString(),
                    surname = surname.text.toString(),
                    university = university.text.toString()
                )
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}