package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest;
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest;
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse;
import com.veyvolopayli.studhunter.domain.repository.AuthRepository;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/AuthRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/AuthRepository;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "prefs", "Landroid/content/SharedPreferences;", "(Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;Landroid/content/SharedPreferences;)V", "authenticate", "", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmailUnique", "email", "isUsernameUnique", "username", "signIn", "Lcom/veyvolopayli/studhunter/domain/model/responses/AuthResponse;", "signInRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/SignInRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/requests/SignInRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "Lretrofit2/Response;", "signUpRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/SignUpRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/requests/SignUpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class AuthRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signUp(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest signUpRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.veyvolopayli.studhunter.domain.model.responses.AuthResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signIn(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.SignInRequest signInRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.responses.AuthResponse> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object authenticate(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isUsernameUnique(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isEmailUnique(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}