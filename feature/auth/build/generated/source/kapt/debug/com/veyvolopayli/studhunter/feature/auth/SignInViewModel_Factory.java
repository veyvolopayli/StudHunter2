package com.veyvolopayli.studhunter.feature.auth;

import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase;
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
public final class SignInViewModel_Factory implements Factory<SignInViewModel> {
  private final Provider<SignInByEmailUseCase> signInByEmailUseCaseProvider;

  public SignInViewModel_Factory(Provider<SignInByEmailUseCase> signInByEmailUseCaseProvider) {
    this.signInByEmailUseCaseProvider = signInByEmailUseCaseProvider;
  }

  @Override
  public SignInViewModel get() {
    return newInstance(signInByEmailUseCaseProvider.get());
  }

  public static SignInViewModel_Factory create(
      Provider<SignInByEmailUseCase> signInByEmailUseCaseProvider) {
    return new SignInViewModel_Factory(signInByEmailUseCaseProvider);
  }

  public static SignInViewModel newInstance(SignInByEmailUseCase signInByEmailUseCase) {
    return new SignInViewModel(signInByEmailUseCase);
  }
}
