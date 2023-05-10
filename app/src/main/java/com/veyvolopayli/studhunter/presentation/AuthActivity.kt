package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.ActivityAuthBinding
import com.veyvolopayli.studhunter.presentation.sign_in_screen.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var signInFragment: SignInFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        signInFragment = SignInFragment()

        binding.signInButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.authFragmentContainer.id, signInFragment)
                addToBackStack(null)
            }.commit()
        }
    }
}