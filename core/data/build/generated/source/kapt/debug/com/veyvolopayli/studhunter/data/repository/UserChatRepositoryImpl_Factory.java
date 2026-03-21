package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class UserChatRepositoryImpl_Factory implements Factory<UserChatRepositoryImpl> {
  private final Provider<HttpClient> clientProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<StudHunterApi> apiProvider;

  public UserChatRepositoryImpl_Factory(Provider<HttpClient> clientProvider,
      Provider<SharedPreferences> prefsProvider, Provider<StudHunterApi> apiProvider) {
    this.clientProvider = clientProvider;
    this.prefsProvider = prefsProvider;
    this.apiProvider = apiProvider;
  }

  @Override
  public UserChatRepositoryImpl get() {
    return newInstance(clientProvider.get(), prefsProvider.get(), apiProvider.get());
  }

  public static UserChatRepositoryImpl_Factory create(Provider<HttpClient> clientProvider,
      Provider<SharedPreferences> prefsProvider, Provider<StudHunterApi> apiProvider) {
    return new UserChatRepositoryImpl_Factory(clientProvider, prefsProvider, apiProvider);
  }

  public static UserChatRepositoryImpl newInstance(HttpClient client, SharedPreferences prefs,
      StudHunterApi api) {
    return new UserChatRepositoryImpl(client, prefs, api);
  }
}
