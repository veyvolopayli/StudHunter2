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
public final class ReviewsRepositoryImpl_Factory implements Factory<ReviewsRepositoryImpl> {
  private final Provider<StudHunterApi> apiProvider;

  public ReviewsRepositoryImpl_Factory(Provider<StudHunterApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ReviewsRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ReviewsRepositoryImpl_Factory create(Provider<StudHunterApi> apiProvider) {
    return new ReviewsRepositoryImpl_Factory(apiProvider);
  }

  public static ReviewsRepositoryImpl newInstance(StudHunterApi api) {
    return new ReviewsRepositoryImpl(api);
  }
}
