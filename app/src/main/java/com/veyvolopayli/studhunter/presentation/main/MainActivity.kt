package com.veyvolopayli.studhunter.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.replaceFragment
import com.veyvolopayli.studhunter.common.showFragment
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.auth_screen.AuthFragment
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.update_app_screen.UpdateAppFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { vm.isLoading.value }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        if (savedInstanceState == null) {
            vm.launchAppResult.observe(this) { launchAppResult ->
                when (launchAppResult) {
                    is LaunchAppResult.NeedToAuthorize -> {
                        // navigate to authorization screen
                        showFragment(binding.mainFragmentContainer.id, AuthFragment(), false)
                    }
                    is LaunchAppResult.NeedToUpdate -> {
                        // navigate to update screen
                        showFragment(binding.mainFragmentContainer.id, UpdateAppFragment(), false)
                    }
                    is LaunchAppResult.ErrorOccurred -> {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                        // navigate to error screen
                        when (launchAppResult.error) {
                            is ErrorType.NetworkError -> {

                            }
                            is ErrorType.ServerError -> {

                            }
                            is ErrorType.UnexpectedError -> {

                            }
                            else -> {

                            }
                        }
                    }
                    is LaunchAppResult.Ok -> {
                        // navigate to home screen
                        vm.showBottomBar()
                        replaceFragment(R.id.main_fragment_container, HomeFragment(), false)
                    }
                }
                vm.appLaunched()
            }
        }

        lifecycleScope.launch {
            vm.isBottomBarVisible.collect { isVisible ->
                binding.bottomNavBar.root.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }

        fun setBottomNavigation() {

        }

    }
}