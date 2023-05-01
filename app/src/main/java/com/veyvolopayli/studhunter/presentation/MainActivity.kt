package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.presentation.publications_list.PublicationsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: PublicationsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.state.observe(this) {
            Log.d("ABA", it.publications.toString())
        }


    }

}