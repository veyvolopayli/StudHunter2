package com.veyvolopayli.studhunter.data.repository;

import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class ChatsRepositoryImpl_Factory implements Factory<ChatsRepositoryImpl> {
  private final Provider<StudHunterApi> apiProvider;

  public ChatsRepositoryImpl_Factory(Provider<StudHunterApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ChatsRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ChatsRepositoryImpl_Factory create(Provider<StudHunterApi> apiProvider) {
    return new ChatsRepositoryImpl_Factory(apiProvider);
  }

  public static ChatsRepositoryImpl newInstance(StudHunterApi api) {
    return new ChatsRepositoryImpl(api);
  }
}
