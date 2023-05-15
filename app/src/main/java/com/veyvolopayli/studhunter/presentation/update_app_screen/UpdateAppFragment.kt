package com.veyvolopayli.studhunter.presentation.update_app_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.DownloadUpdateResult
import com.veyvolopayli.studhunter.databinding.FragmentUpdateAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateAppFragment : Fragment() {

    private val viewModel: UpdateAppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUpdateAppBinding.inflate(layoutInflater, container, false)

        binding.updateAppButton.setOnClickListener {
            viewModel.downloadUpdate(requireContext(), requireActivity())
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is DownloadUpdateResult.Downloaded -> {
                    binding.loadingLayout.root.visibility = View.GONE
                }
                is DownloadUpdateResult.Downloading -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }
                is DownloadUpdateResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

}