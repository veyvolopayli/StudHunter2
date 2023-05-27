package com.veyvolopayli.studhunter.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.veyvolopayli.studhunter.databinding.ActivityMainBinding
import com.veyvolopayli.studhunter.presentation.start_screen.StartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    /*
    * Если Activity запустилась в первый раз, должно быть проверено наличие обновления,
    * если обновление есть, то навигация на экран обновления без авторизации, иначе запуск юзкейса
    * для проверки авторизации и последующая навигация
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                vm.isLoading.value
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.fullscreenMainFragmentContainer.id, StartFragment())
                addToBackStack(null)
            }
        }
    }
}