package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
import com.veyvolopayli.studhunter.common.Constants;
import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.domain.model.DetailedChat;
import com.veyvolopayli.studhunter.domain.repository.ChatsRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/ChatsRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/ChatsRepository;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "(Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;)V", "getChats", "", "Lcom/veyvolopayli/studhunter/domain/model/DetailedChat;", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class ChatsRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.ChatsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    
    @javax.inject.Inject()
    public ChatsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getChats(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.DetailedChat>> $completion) {
        return null;
    }
}