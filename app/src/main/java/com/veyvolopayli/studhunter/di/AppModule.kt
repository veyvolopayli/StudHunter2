package com.veyvolopayli.studhunter.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.veyvolopayli.studhunter.data.repository.PublicationRepositoryImpl
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.repository.AuthRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.UpdateRepositoryImpl
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import com.veyvolopayli.studhunter.domain.usecases.auth.AuthenticateUseCase
import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

/*    @Provides
    fun providesApplication(app: Application): Application {
        return app
    }*/

    @Provides
    @Singleton
    fun providePublicationRepository(api: StudHunterApi): PublicationRepository {
        return PublicationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideStudHunterApi(): StudHunterApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudHunterApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSharedPrefs(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: StudHunterApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providesUpdateRepository(api: StudHunterApi): UpdateRepository {
        return UpdateRepositoryImpl(api)
    }

}