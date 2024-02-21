package com.veyvolopayli.studhunter.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commitNow
import androidx.navigation.ui.setupWithNavController
import com.mifmif.common.regex.Main
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.obtainNavHostFragment
import com.veyvolopayli.studhunter.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        this.binding = binding

        val fragmentContainer = binding.fragmentContainer
        val navHostFragment = fragmentContainer.obtainNavHostFragment(R.navigation.nav_graph)

        childFragmentManager.commitNow {
            replace(binding.fragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        binding.navigationView.setupWithNavController(navHostFragment.navController)
        mainViewModel.stopLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}