package com.veyvolopayli.studhunter.presentation.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.common.fragments.replaceFragment
import com.veyvolopayli.studhunter.databinding.FragmentStartBinding
import com.veyvolopayli.studhunter.presentation.auth_screen.AuthFragment
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.update_app_screen.UpdateAppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val vm: StartViewModel by viewModels()
//    val fragmentFeatures()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        vm.checkUpdateResult.observe(viewLifecycleOwner) { checkUpdateResult ->
            when (checkUpdateResult) {
                is CheckUpdateResult.UpdateAvailable -> {
                    replaceFragment(
                        container = R.id.fullscreen_main_fragment_container,
                        newFragment = UpdateAppFragment(),
                        addToBackStack = false
                    )
                }
                is CheckUpdateResult.LastVersionInstalled -> {
                    replaceFragment(
                        container = R.id.main_fragment_container,
                        newFragment = AuthFragment(),
                        addToBackStack = false
                    )
                    parentFragmentManager.beginTransaction().remove(this).commit()
                }
                is CheckUpdateResult.Error -> {
                    Toast.makeText(requireContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

}