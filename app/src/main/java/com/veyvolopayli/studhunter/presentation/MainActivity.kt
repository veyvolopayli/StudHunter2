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
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.start_screen.StartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            binding.bottomNavBar.bottomNavLl.visibility = View.GONE
            supportFragmentManager.commit {
                replace(binding.mainFragmentContainer.id, StartFragment())
                addToBackStack(null)
            }
        }

    }

}