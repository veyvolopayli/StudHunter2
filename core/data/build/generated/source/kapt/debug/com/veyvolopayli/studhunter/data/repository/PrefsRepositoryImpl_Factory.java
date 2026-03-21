package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
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
public final class PrefsRepositoryImpl_Factory implements Factory<PrefsRepositoryImpl> {
  private final Provider<SharedPreferences> prefsProvider;

  public PrefsRepositoryImpl_Factory(Provider<SharedPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public PrefsRepositoryImpl get() {
    return newInstance(prefsProvider.get());
  }

  public static PrefsRepositoryImpl_Factory create(Provider<SharedPreferences> prefsProvider) {
    return new PrefsRepositoryImpl_Factory(prefsProvider);
  }

  public static PrefsRepositoryImpl newInstance(SharedPreferences prefs) {
    return new PrefsRepositoryImpl(prefs);
  }
}
