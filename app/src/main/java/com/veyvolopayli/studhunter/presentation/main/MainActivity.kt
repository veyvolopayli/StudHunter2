package com.veyvolopayli.studhunter.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.fragments.showFragment
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.auth_screen.AuthFragment
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.update_app_screen.UpdateAppFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    /*
    * Если Activity запустилась в первый раз, должно быть проверено наличие обновления,
    * если обновление есть, то навигация на экран обновления без авторизации, иначе запуск юзкейса
    * для проверки авторизации и последующая навигация
    *
    * Bottom bar должен принимать состояние видимого только после навигации на HomeScreen
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { vm.isLoading.value }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
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
                        showFragment(binding.mainFragmentContainer.id, HomeFragment(), false)
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
    }
}