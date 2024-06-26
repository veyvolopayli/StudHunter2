package com.veyvolopayli.studhunter.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.repository.AuthRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.ChatsRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.GalleryRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.PrefsRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.PublicationRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.ReviewsRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.UpdateRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.UserChatRepositoryImpl
import com.veyvolopayli.studhunter.data.repository.UserRepositoryImpl
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import com.veyvolopayli.studhunter.domain.repository.ChatsRepository
import com.veyvolopayli.studhunter.domain.repository.GalleryRepository
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import com.veyvolopayli.studhunter.domain.repository.ReviewsRepository
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserRepository(api: StudHunterApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePublicationRepository(api: StudHunterApi): PublicationRepository {
        return PublicationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideStudHunterApi(): StudHunterApi {
        val okHttpClient = OkHttpClient.Builder().build()
        val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
            .create(StudHunterApi::class.java)
    }

    @Provides
    @Singleton
    fun providesContentResolver(app: Application): ContentResolver {
        return app.contentResolver
    }

    @Provides
    @Singleton
    fun providesGalleryRepository(contentResolver: ContentResolver): GalleryRepository {
        return GalleryRepositoryImpl(contentResolver)
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

    @Provides
    @Singleton
    fun providesKtorHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }
            install(Logging)
        }
    }

    @Provides
    @Singleton
    fun providesUserChatRepository(client: HttpClient, prefs: SharedPreferences, api: StudHunterApi): UserChatRepository {
        return UserChatRepositoryImpl(client, prefs, api)
    }

    @Provides
    @Singleton
    fun providesChatsRepository(api: StudHunterApi): ChatsRepository {
        return ChatsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideReviewsRepository(api: StudHunterApi): ReviewsRepository {
        return ReviewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePrefsRepository(prefs: SharedPreferences): PrefsRepository {
        return PrefsRepositoryImpl(prefs)
    }

}