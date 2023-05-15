package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.veyvolopayli.studhunter.presentation.home_screen.HomeViewModel
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.start_screen.StartFragment
import com.veyvolopayli.studhunter.presentation.update_app_screen.UpdateAppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {

            viewModel.checkUpdateResult.observe(this) { result ->
                when (result) {
                    is CheckUpdateResult.UpdateAvailable -> {
                        supportFragmentManager.commit {
                            replace(binding.mainFragmentContainer.id, UpdateAppFragment())
                        }
                    }
                    is CheckUpdateResult.LastVersionInstalled -> {
                        Toast.makeText(this, "LastVersionInstalled", Toast.LENGTH_SHORT).show()
                    }
                    is CheckUpdateResult.Error -> {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                    }
                }
            }

            binding.bottomNavBar.bottomNavLl.visibility = View.GONE
            supportFragmentManager.commit {
                replace(binding.mainFragmentContainer.id, StartFragment())
                addToBackStack(null)
            }
        }
    }
}