package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.veyvolopayli.studhunter.presentation.home_screen.HomeViewModel
import com.veyvolopayli.studhunter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val token = intent.getStringExtra("token")

//        Toast.makeText(this, token, Toast.LENGTH_SHORT).show()

        vm.state.observe(this) {
            Log.e("Publications", it.publications.toString())
        }

    }

}