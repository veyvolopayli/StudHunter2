package com.veyvolopayli.studhunter.feature.auth;

import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase;
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
public final class SignUpViewModel_Factory implements Factory<SignUpViewModel> {
  private final Provider<SignUpByEmailUseCase> signUpByEmailUseCaseProvider;

  public SignUpViewModel_Factory(Provider<SignUpByEmailUseCase> signUpByEmailUseCaseProvider) {
    this.signUpByEmailUseCaseProvider = signUpByEmailUseCaseProvider;
  }

  @Override
  public SignUpViewModel get() {
    return newInstance(signUpByEmailUseCaseProvider.get());
  }

  public static SignUpViewModel_Factory create(
      Provider<SignUpByEmailUseCase> signUpByEmailUseCaseProvider) {
    return new SignUpViewModel_Factory(signUpByEmailUseCaseProvider);
  }

  public static SignUpViewModel newInstance(SignUpByEmailUseCase signUpByEmailUseCase) {
    return new SignUpViewModel(signUpByEmailUseCase);
  }
}
