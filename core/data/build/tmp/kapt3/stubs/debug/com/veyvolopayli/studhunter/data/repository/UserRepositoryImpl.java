package com.veyvolopayli.studhunter.data.repository;

import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.domain.model.University;
import com.veyvolopayli.studhunter.domain.model.User;
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest;
import com.veyvolopayli.studhunter.domain.repository.UserRepository;
import com.veyvolopayli.studhunter.domain.model.WideTask;
import okhttp3.MultipartBody;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J4\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0013H\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00132\u0006\u0010 \u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010!\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/UserRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/UserRepository;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "(Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;)V", "editProfile", "", "token", "", "editProfileRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/EditProfileRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/requests/EditProfileRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchUserById", "Lcom/veyvolopayli/studhunter/domain/model/User;", "id", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUserId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyPublications", "", "Lcom/veyvolopayli/studhunter/domain/model/MyPublication;", "getTasks", "Lcom/veyvolopayli/studhunter/domain/model/WideTask;", "userId", "userStatus", "taskStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUniversities", "Lcom/veyvolopayli/studhunter/domain/model/University;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserPublications", "Lcom/veyvolopayli/studhunter/domain/model/Publication;", "userID", "uploadProfileImage", "image", "Lokhttp3/MultipartBody$Part;", "(Ljava/lang/String;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class UserRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    
    @javax.inject.Inject()
    public UserRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchUserById(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.User> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCurrentUserId(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getUniversities(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.University>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getUserPublications(@org.jetbrains.annotations.NotNull()
    java.lang.String userID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Publication>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMyPublications(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.MyPublication>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object editProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest editProfileRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadProfileImage(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String userStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String taskStatus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.WideTask>> $completion) {
        return null;
    }
}