package com.veyvolopayli.studhunter.presentation.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.navigation.NavOptions
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.obtainNavHostFragment
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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

        val fragmentContainer = binding.fullscreenFragmentContainer
        val navHostFragment = fragmentContainer.obtainNavHostFragment(R.navigation.fullscreen_nav_graph)

        supportFragmentManager.commit {
            replace(binding.fullscreenFragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        if (savedInstanceState == null) {
            vm.launchAppResult.observe(this) { launchAppResult ->
                when (launchAppResult) {
                    is LaunchAppResult.NotAuthorized -> {
                        // navigate to authorization screen
//                        vm.showBottomBar()
                    }
                    is LaunchAppResult.UpdateAvailable -> {
                        // navigate to update screen
                    }
                    is LaunchAppResult.Error -> {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                        // navigate to error screen
                        when (launchAppResult.error) {
                            is ErrorType.LocalError -> {

                            }
                            is ErrorType.ServerError -> {

                            }
                            is ErrorType.NetworkError -> {

                            }
                            else -> {

                            }
                        }
                    }
                    is LaunchAppResult.Success -> {
                        val navOptions = NavOptions.Builder().setPopUpTo(R.id.authFragment, true).build()
                        navHostFragment.navController.navigate(R.id.action_authFragment_to_mainFragment, null, navOptions)
                    }
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
            }
        }

    }
}