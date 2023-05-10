package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.veyvolopayli.studhunter.presentation.home_screen.HomeViewModel
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
//    private val navController: NavController by lazy { findNavController(R.id.nav_graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}