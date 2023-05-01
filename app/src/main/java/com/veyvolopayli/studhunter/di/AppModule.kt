package com.veyvolopayli.studhunter.di

import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.repository.PublicationRepositoryImpl
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetPublicationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStudHunterApi(): StudHunterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudHunterApi::class.java)
    }

    @Provides
    @Singleton
    fun providePublicationRepository(api: StudHunterApi): PublicationRepository {
        return PublicationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetPublicationsUseCase(publicationsRepositoryImpl: PublicationRepositoryImpl): GetPublicationsUseCase {
        return GetPublicationsUseCase(publicationsRepositoryImpl)
    }

}