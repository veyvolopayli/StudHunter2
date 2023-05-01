package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.repository.PublicationRepositoryImpl
import com.veyvolopayli.studhunter.di.AppModule
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsUseCase
import com.veyvolopayli.studhunter.presentation.publications_list.PublicationsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: PublicationsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val api = AppModule.provideStudHunterApi()

        val getPublicationsUseCase = GetPublicationsUseCase(PublicationRepositoryImpl(api))

        vm = PublicationsListViewModel(getPublicationsUseCase)*/

        vm.state.observe(this) {
            Log.e("L", it.publications.toString() ?: "")
        }


    }

}