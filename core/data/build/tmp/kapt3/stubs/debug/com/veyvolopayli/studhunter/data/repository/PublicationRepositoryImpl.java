package com.veyvolopayli.studhunter.data.repository;

import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.domain.model.DetailedPublication;
import com.veyvolopayli.studhunter.domain.model.FilterRequest;
import com.veyvolopayli.studhunter.domain.model.Publication;
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload;
import com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest;
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository;
import okhttp3.MultipartBody;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u001dH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010 \u001a\u00020!H\u0096@\u00a2\u0006\u0002\u0010\"J\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u001dH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010$\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010&\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\'J,\u0010(\u001a\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00192\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/PublicationRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/PublicationRepository;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "(Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;)V", "addPubToFavorite", "", "token", "", "changePubFavoriteStatusRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/ChangePubFavoriteStatusRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/requests/ChangePubFavoriteStatusRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkImageValidity", "", "publicationId", "num", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkPubFavoriteStatus", "pubID", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchPublication", "Lcom/veyvolopayli/studhunter/domain/model/DetailedPublication;", "id", "fetchPublications", "", "Lcom/veyvolopayli/studhunter/domain/model/Publication;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategories", "", "getDistricts", "getFilteredPublications", "filterRequest", "Lcom/veyvolopayli/studhunter/domain/model/FilterRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/FilterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPriceTypes", "removePubFromFavorite", "searchPublications", "query", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadPublication", "imageFiles", "Lokhttp3/MultipartBody$Part;", "publicationData", "Lcom/veyvolopayli/studhunter/domain/model/PublicationToUpload;", "(Ljava/util/List;Lcom/veyvolopayli/studhunter/domain/model/PublicationToUpload;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class PublicationRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.PublicationRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    
    public PublicationRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchPublications(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Publication>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchPublication(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.DetailedPublication> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkImageValidity(@org.jetbrains.annotations.NotNull()
    java.lang.String publicationId, int num, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Integer, java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadPublication(@org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> imageFiles, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.PublicationToUpload publicationData, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPriceTypes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Integer, java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDistricts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addPubToFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest changePubFavoriteStatusRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removePubFromFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest changePubFavoriteStatusRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkPubFavoriteStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String pubID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object searchPublications(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Publication>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getFilteredPublications(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.FilterRequest filterRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.Publication>> $completion) {
        return null;
    }
}