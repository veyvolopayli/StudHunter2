package com.veyvolopayli.studhunter.feature.auth;

import androidx.lifecycle.ViewModel;
import com.veyvolopayli.studhunter.common.AuthorizationResult;
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest;
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/veyvolopayli/studhunter/feature/auth/SignUpViewModel;", "Landroidx/lifecycle/ViewModel;", "signUpByEmailUseCase", "Lcom/veyvolopayli/studhunter/domain/usecases/auth/SignUpByEmailUseCase;", "(Lcom/veyvolopayli/studhunter/domain/usecases/auth/SignUpByEmailUseCase;)V", "_events", "Lkotlinx/coroutines/channels/Channel;", "Lcom/veyvolopayli/studhunter/feature/auth/SignUpEvent;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/veyvolopayli/studhunter/feature/auth/SignUpUiState;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onEmailChange", "", "v", "", "onNameChange", "onPasswordChange", "onSurnameChange", "onUniversityChange", "onUsernameChange", "trySignUp", "auth_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SignUpViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase signUpByEmailUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.veyvolopayli.studhunter.feature.auth.SignUpUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.veyvolopayli.studhunter.feature.auth.SignUpUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.veyvolopayli.studhunter.feature.auth.SignUpEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.veyvolopayli.studhunter.feature.auth.SignUpEvent> events = null;
    
    @javax.inject.Inject()
    public SignUpViewModel(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase signUpByEmailUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.veyvolopayli.studhunter.feature.auth.SignUpUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.veyvolopayli.studhunter.feature.auth.SignUpEvent> getEvents() {
        return null;
    }
    
    public final void onUsernameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onSurnameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onUniversityChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void trySignUp() {
    }
}