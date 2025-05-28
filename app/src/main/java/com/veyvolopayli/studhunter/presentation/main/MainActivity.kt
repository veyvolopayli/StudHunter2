package com.veyvolopayli.studhunter.presentation.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withCreated
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.obtainNavHostFragment
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        /** Splash-screen держится, пока VM в состоянии Loading */
        installSplashScreen().setKeepOnScreenCondition {
            vm.state.value is UiState.Loading
        }

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavHost()
        observeUi()
    }

    private fun setUpNavHost() {
        val navHostFragment = binding.fullscreenFragmentContainer
            .obtainNavHostFragment(R.navigation.fullscreen_nav_graph)

        supportFragmentManager.commitNow {
            replace(binding.fullscreenFragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        navController = navHostFragment.navController
    }

    /** Следим за состоянием UI, приходящим из ViewModel */
    private fun observeUi() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.state.collect { state ->
                when (state) {
                    UiState.Loading -> Unit
                    UiState.NoInternet -> navToNoInternet()
                    UiState.UpdateAvailable -> navToUpdate()
                    UiState.Authorized -> navToMain()
                    UiState.NotAuthorized -> navToAuth()
                    is UiState.Error -> showError(state.error)
                }

                // Разрешаем свободную ориентацию, когда приложение запущено
                if (state !is UiState.Loading) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                }
            }
        }
    }

    /* ---------- Навигация ---------- */

    private fun navToMain() {
        val opts = NavOptions.Builder()
            .setPopUpTo(R.id.authFragment, true)
            .build()
        navController.navigate(R.id.action_authFragment_to_mainFragment, null, opts)
    }

    private fun navToAuth() {
        val opts = NavOptions.Builder()
            .setPopUpTo(R.id.mainFragment, true)
            .build()
        navController.navigate(R.id.authFragment, null, opts)
    }

    private fun navToUpdate() {
//        navController.navigate(R.id.action_global_updateFragment)
    }

    private fun navToNoInternet() {
        navController.navigate(R.id.action_authFragment_to_noInternetFragment)
    }

    private fun showError(error: ErrorType?) {
        val message = when (error) {
            is ErrorType.NetworkError -> getString(R.string.network_error)
            is ErrorType.ServerError -> getString(R.string.server_error)
            is ErrorType.LocalError -> getString(R.string.local_error)
            else -> getString(R.string.unknown_error)
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
