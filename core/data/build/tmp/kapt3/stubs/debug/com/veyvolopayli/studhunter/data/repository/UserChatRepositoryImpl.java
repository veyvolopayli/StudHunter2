package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
import android.util.Log;
import com.veyvolopayli.studhunter.domain.model.chat.DealRequest;
import com.veyvolopayli.studhunter.common.Constants;
import com.veyvolopayli.studhunter.common.ErrorType;
import com.veyvolopayli.studhunter.common.Resource;
import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO;
import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer;
import com.veyvolopayli.studhunter.domain.model.chat.IncomingTextFrame;
import com.veyvolopayli.studhunter.domain.model.chat.OutgoingMessage;
import com.veyvolopayli.studhunter.domain.model.chat.Task;
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketSession;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001d2\u0006\u0010\u001e\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\u001d2\u0006\u0010!\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J\u0016\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020+H\u0096@\u00a2\u0006\u0002\u0010,J\u0016\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/UserChatRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/UserChatRepository;", "client", "Lio/ktor/client/HttpClient;", "prefs", "Landroid/content/SharedPreferences;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "(Lio/ktor/client/HttpClient;Landroid/content/SharedPreferences;Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;)V", "json", "Lkotlinx/serialization/json/Json;", "session", "Lio/ktor/websocket/WebSocketSession;", "disconnect", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMessagesByChatId", "", "Lcom/veyvolopayli/studhunter/domain/model/Message;", "token", "", "chatId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMessagesByPublicationId", "pubId", "getTaskByChatId", "Lcom/veyvolopayli/studhunter/domain/model/chat/Task;", "getTaskByPubId", "initSession", "Lcom/veyvolopayli/studhunter/common/Resource;", "chatID", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initSessionForNew", "pubID", "observeMessages", "Lkotlinx/coroutines/flow/Flow;", "Lcom/veyvolopayli/studhunter/domain/model/chat/IncomingTextFrame;", "sendMessage", "message", "Lcom/veyvolopayli/studhunter/domain/model/chat/OutgoingMessage;", "(Lcom/veyvolopayli/studhunter/domain/model/chat/OutgoingMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOfferRequest", "jobDeadline", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOfferResponse", "task", "(Lcom/veyvolopayli/studhunter/domain/model/chat/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class UserChatRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.UserChatRepository {
    @org.jetbrains.annotations.NotNull()
    private final io.ktor.client.HttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    @org.jetbrains.annotations.Nullable()
    private io.ktor.websocket.WebSocketSession session;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.serialization.json.Json json = null;
    
    @javax.inject.Inject()
    public UserChatRepositoryImpl(@org.jetbrains.annotations.NotNull()
    io.ktor.client.HttpClient client, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object initSessionForNew(@org.jetbrains.annotations.NotNull()
    java.lang.String pubID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.common.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object initSession(@org.jetbrains.annotations.NotNull()
    java.lang.String chatID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.common.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.chat.OutgoingMessage message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendOfferRequest(long jobDeadline, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendOfferResponse(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.chat.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMessagesByChatId(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Message>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMessagesByPublicationId(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String pubId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Message>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTaskByChatId(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.chat.Task> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTaskByPubId(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String pubId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.chat.Task> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object disconnect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.veyvolopayli.studhunter.domain.model.chat.IncomingTextFrame> observeMessages() {
        return null;
    }
}