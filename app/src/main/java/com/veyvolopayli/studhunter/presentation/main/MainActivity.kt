package com.veyvolopayli.studhunter.presentation.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val vm: MainViewModel by viewModels()

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { vm.isLoading.value }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.main_fragment_container
        ) as NavHostFragment

        navController = navHostFragment.navController

        navController?.let {
            binding.bottomNavigationBar.setupWithNavController(it)
        }

        /*binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {

                }
                R.id.categories -> {

                }
                R.id.upload_publication -> {

                }
                R.id.chats -> {

                }
                R.id.profile -> {

                }
            }
            true
        }*/

        if (savedInstanceState == null) {
            vm.launchAppResult.observe(this) { launchAppResult ->
                when (launchAppResult) {
                    is LaunchAppResult.NotAuthorized -> {
                        // navigate to authorization screen
                        vm.showBottomBar()
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
                        vm.showBottomBar()
                    }
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
            }
        }

        lifecycleScope.launch {
            vm.isBottomBarVisible.collect { isVisible ->
                binding.bottomNavigationBar.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}