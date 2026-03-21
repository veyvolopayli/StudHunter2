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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<StudHunterApi> apiProvider;

  public UserRepositoryImpl_Factory(Provider<StudHunterApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<StudHunterApi> apiProvider) {
    return new UserRepositoryImpl_Factory(apiProvider);
  }

  public static UserRepositoryImpl newInstance(StudHunterApi api) {
    return new UserRepositoryImpl(api);
  }
}
