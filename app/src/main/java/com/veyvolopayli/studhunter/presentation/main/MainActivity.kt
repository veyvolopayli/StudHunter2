package com.veyvolopayli.studhunter.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.veyvolopayli.studhunter.Application.Companion.INSTANCE
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Screens
import com.veyvolopayli.studhunter.common.replaceFragment
import com.veyvolopayli.studhunter.common.showFragment
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.auth_screen.AuthFragment
import com.veyvolopayli.studhunter.presentation.categories_screen.CategoriesFragment
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import com.veyvolopayli.studhunter.presentation.image_gallery.GalleryFragment
import com.veyvolopayli.studhunter.presentation.publication_screen.PublicationFragment
import com.veyvolopayli.studhunter.presentation.update_app_screen.UpdateAppFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Stack

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val vm: MainViewModel by viewModels()

    private val homeFragment = HomeFragment()
    private val categoriesFragment = CategoriesFragment()

    private var home: FragmentScreen = Screens.home()
    private var categories: FragmentScreen = Screens.categories()

    private val navigator = AppNavigator(this, R.id.main_fragment_container)

    private var currentFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { vm.isLoading.value }
        }

        INSTANCE.navigatorHolder.setNavigator(navigator)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        if (savedInstanceState == null) {
            vm.launchAppResult.observe(this) { launchAppResult ->
                when (launchAppResult) {
                    is LaunchAppResult.NeedToAuthorize -> {
                        // navigate to authorization screen
                        replaceFragment(binding.mainFragmentContainer.id, AuthFragment())
                    }
                    is LaunchAppResult.NeedToUpdate -> {
                        // navigate to update screen
                        replaceFragment(binding.mainFragmentContainer.id, UpdateAppFragment())
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
//                        router.replaceScreen(home)
                        lifecycleScope.launch(Dispatchers.Main) {
                            replaceFragment(container = binding.mainFragmentContainer.id, newFragment = homeFragment)
                        }
                    }
                }
                vm.appLaunched()
            }
        }

        setBottomNavigation(binding)

        vm.navigationEvent.observe(this) { destination ->
            when (destination) {
                is MainNavDestination.Home -> {
                    showFragment(container = binding.mainFragmentContainer.id, currentFragment = destination.previousDestination ?: currentFragment, newFragment = homeFragment)
                    currentFragment = homeFragment
                }
                is MainNavDestination.Categories -> {
                    showFragment(container = binding.mainFragmentContainer.id, currentFragment = destination.previousDestination ?: currentFragment, newFragment = categoriesFragment)
                    currentFragment = categoriesFragment
                }
                is MainNavDestination.Upload -> Unit
                is MainNavDestination.Favorites -> Unit
                is MainNavDestination.Profile -> Unit
                is MainNavDestination.Filter -> Unit
                is MainNavDestination.Search -> Unit
                is MainNavDestination.Publication -> {
                    val publicationFragment = PublicationFragment()
                    publicationFragment.arguments = destination.bundle
                    showFragment(R.id.main_fragment_container, destination.previousDestination ?: currentFragment, publicationFragment, null)
                    destination.previousDestination?.let { currentFragment = it }
                }
                is MainNavDestination.Gallery -> {
                    replaceFragment(container = binding.mainFragmentContainer.id, newFragment = GalleryFragment())
                }
            }
        }

        lifecycleScope.launch {
            vm.isBottomBarVisible.collect { isVisible ->
                binding.bottomNavBar.root.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }

    }

    private fun setBottomNavigation(binding: ActivityMainBinding) {
        binding.bottomNavBar.home.setOnClickListener { vm.navigateTo(MainNavDestination.Home(currentFragment)) }
        binding.bottomNavBar.categories.setOnClickListener { vm.navigateTo(MainNavDestination.Categories(currentFragment)) }
        binding.bottomNavBar.upload.setOnClickListener { vm.navigateTo(MainNavDestination.Gallery(currentFragment)) }
        binding.bottomNavBar.favourites.setOnClickListener { vm.navigateTo(MainNavDestination.Favorites(currentFragment)) }
        binding.bottomNavBar.profile.setOnClickListener { vm.navigateTo(MainNavDestination.Profile(currentFragment)) }
    }
}