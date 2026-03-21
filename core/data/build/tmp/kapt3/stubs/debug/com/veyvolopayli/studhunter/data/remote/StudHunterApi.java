package com.veyvolopayli.studhunter.data.remote;

import com.veyvolopayli.studhunter.common.SimpleResponse;
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO;
import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO;
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto;
import com.veyvolopayli.studhunter.domain.model.DetailedChat;
import com.veyvolopayli.studhunter.data.remote.dto.DetailedPublicationDto;
import com.veyvolopayli.studhunter.domain.model.FilterRequest;
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload;
import com.veyvolopayli.studhunter.domain.model.University;
import com.veyvolopayli.studhunter.domain.model.User;
import com.veyvolopayli.studhunter.domain.model.WideTask;
import com.veyvolopayli.studhunter.domain.model.chat.Task;
import com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest;
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest;
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest;
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest;
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse;
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse;
import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest;
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00dc\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u00020\n2\b\b\u0001\u0010\r\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0012\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0017\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\"\u0010\u001a\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\"\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010 \u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\"\u0010$\u001a\u00020%2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010 \u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\'H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\"2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050+2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0001\u0010.\u001a\u00020/H\u00a7@\u00a2\u0006\u0002\u00100J(\u00101\u001a\b\u0012\u0004\u0012\u0002020\"2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u00103\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J(\u00104\u001a\b\u0012\u0004\u0012\u0002020\"2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u00105\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u00106\u001a\b\u0012\u0004\u0012\u0002070\"2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\'H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\"\u00109\u001a\u00020:2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u00103\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\"\u0010;\u001a\u00020:2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u00105\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J<\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\"2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010>\u001a\u00020\u00052\b\b\u0001\u0010?\u001a\u00020\u00052\b\b\u0001\u0010@\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010AJ\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\"H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010D\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0001\u0010E\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010F\u001a\u00020\n2\b\b\u0001\u0010G\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010H\u001a\u00020\n2\b\b\u0001\u0010I\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010J\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010K\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0001\u0010L\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010M\u001a\u00020N2\b\b\u0001\u0010O\u001a\u00020PH\u00a7@\u00a2\u0006\u0002\u0010QJ\u001e\u0010R\u001a\b\u0012\u0004\u0012\u00020N0S2\b\b\u0001\u0010T\u001a\u00020UH\u00a7@\u00a2\u0006\u0002\u0010VJ\"\u0010W\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010X\u001a\u00020YH\u00a7@\u00a2\u0006\u0002\u0010ZJ2\u0010[\u001a\u00020\u00052\u000e\b\u0001\u0010\\\u001a\b\u0012\u0004\u0012\u00020Y0\"2\b\b\u0001\u0010]\u001a\u00020^2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010_J\"\u0010`\u001a\u00020a2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010b\u001a\u00020cH\u00a7@\u00a2\u0006\u0002\u0010d\u00a8\u0006e"}, d2 = {"Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "", "addPubToFavorite", "", "token", "", "changePubFavoriteStatusRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/ChangePubFavoriteStatusRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/requests/ChangePubFavoriteStatusRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticate", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkImageValidity", "publicationId", "n", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkPubFavoriteStatus", "pubID", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkUpdate", "Lcom/veyvolopayli/studhunter/domain/model/responses/CheckUpdateResponse;", "version", "downloadUpdate", "Lokhttp3/ResponseBody;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "editProfile", "editProfileRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/EditProfileRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/requests/EditProfileRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchPublication", "Lcom/veyvolopayli/studhunter/data/remote/dto/DetailedPublicationDto;", "id", "fetchPublications", "", "Lcom/veyvolopayli/studhunter/data/remote/dto/PublicationDto;", "fetchUserById", "Lcom/veyvolopayli/studhunter/domain/model/User;", "getCategories", "", "getChats", "Lcom/veyvolopayli/studhunter/domain/model/DetailedChat;", "getCurrentUserId", "Lcom/veyvolopayli/studhunter/common/SimpleResponse;", "getDistricts", "getFilteredPublications", "filterRequest", "Lcom/veyvolopayli/studhunter/domain/model/FilterRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/FilterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMessagesByChatId", "Lcom/veyvolopayli/studhunter/data/remote/dto/MessageDTO;", "chatId", "getMessagesByPublicationId", "pubId", "getMyPublications", "Lcom/veyvolopayli/studhunter/data/remote/dto/MyPublicationDTO;", "getPriceTypes", "getTaskByChatId", "Lcom/veyvolopayli/studhunter/domain/model/chat/Task;", "getTaskByPubId", "getTasks", "Lcom/veyvolopayli/studhunter/domain/model/WideTask;", "userId", "userStatus", "taskStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUniversities", "Lcom/veyvolopayli/studhunter/domain/model/University;", "getUserPublications", "userID", "isEmailUnique", "email", "isUsernameUnique", "username", "removePubFromFavorite", "searchPublications", "query", "signIn", "Lcom/veyvolopayli/studhunter/domain/model/responses/AuthResponse;", "signInRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/SignInRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/requests/SignInRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "Lretrofit2/Response;", "signUpRequest", "Lcom/veyvolopayli/studhunter/domain/model/requests/SignUpRequest;", "(Lcom/veyvolopayli/studhunter/domain/model/requests/SignUpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadAvatar", "avatar", "Lokhttp3/MultipartBody$Part;", "(Ljava/lang/String;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadPublication", "imageFiles", "publicationData", "Lcom/veyvolopayli/studhunter/domain/model/PublicationToUpload;", "(Ljava/util/List;Lcom/veyvolopayli/studhunter/domain/model/PublicationToUpload;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadReview", "Lcom/veyvolopayli/studhunter/domain/model/review/ReviewDto;", "newReviewRequest", "Lcom/veyvolopayli/studhunter/domain/model/review/NewReviewRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/review/NewReviewRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface StudHunterApi {
    
    @retrofit2.http.GET(value = "publications/fetch")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchPublications(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.PublicationDto>> $completion);
    
    @retrofit2.http.POST(value = "signin")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signIn(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.SignInRequest signInRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.responses.AuthResponse> $completion);
    
    @retrofit2.http.POST(value = "signup")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signUp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest signUpRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.veyvolopayli.studhunter.domain.model.responses.AuthResponse>> $completion);
    
    @retrofit2.http.GET(value = "update/check/{version}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkUpdate(@retrofit2.http.Path(value = "version")
    @org.jetbrains.annotations.NotNull()
    java.lang.String version, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse> $completion);
    
    @retrofit2.http.GET(value = "update/download/last")
    @retrofit2.http.Streaming()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object downloadUpdate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> $completion);
    
    @retrofit2.http.GET(value = "user/public/get")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isUsernameUnique(@retrofit2.http.Query(value = "username")
    @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "user/public/get")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isEmailUnique(@retrofit2.http.Query(value = "email")
    @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "publications/id/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchPublication(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.data.remote.dto.DetailedPublicationDto> $completion);
    
    @retrofit2.http.GET(value = "image/{publicationId}/image_{n}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkImageValidity(@retrofit2.http.Path(value = "publicationId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String publicationId, @retrofit2.http.Path(value = "n")
    int n, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "authenticate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object authenticate(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "user/get")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchUserById(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.User> $completion);
    
    @retrofit2.http.GET(value = "userid")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUserId(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.common.SimpleResponse<java.lang.String>> $completion);
    
    @retrofit2.http.GET(value = "publication/categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Integer, java.lang.String>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "publications/new")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadPublication(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> imageFiles, @retrofit2.http.Part(value = "publicationData")
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.PublicationToUpload publicationData, @retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @retrofit2.http.GET(value = "publication/priceTypes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPriceTypes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Integer, java.lang.String>> $completion);
    
    @retrofit2.http.GET(value = "publication/districts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDistricts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @retrofit2.http.GET(value = "universities/get")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUniversities(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.University>> $completion);
    
    @retrofit2.http.GET(value = "chats/get")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChats(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.DetailedChat>> $completion);
    
    @retrofit2.http.GET(value = "favorites/publication/{id}/check")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkPubFavoriteStatus(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String pubID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @retrofit2.http.POST(value = "favorites/publication/add")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addPubToFavorite(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest changePubFavoriteStatusRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @retrofit2.http.POST(value = "favorites/publication/remove-single")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removePubFromFavorite(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest changePubFavoriteStatusRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @retrofit2.http.GET(value = "user/{id}/publications")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserPublications(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.PublicationDto>> $completion);
    
    @retrofit2.http.GET(value = "my-publications")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMyPublications(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "avatar/upload")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadAvatar(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part avatar, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @retrofit2.http.POST(value = "profile/edit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object editProfile(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest editProfileRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @retrofit2.http.GET(value = "publications/query/{query}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchPublications(@retrofit2.http.Path(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.PublicationDto>> $completion);
    
    @retrofit2.http.GET(value = "chat/by-chat_id/{chatID}/messages")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMessagesByChatId(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "chatID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.MessageDTO>> $completion);
    
    @retrofit2.http.GET(value = "chat/by-publication_id/{pubID}/messages")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMessagesByPublicationId(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "pubID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String pubId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.MessageDTO>> $completion);
    
    @retrofit2.http.POST(value = "publications/filtered")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFilteredPublications(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.FilterRequest filterRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.data.remote.dto.PublicationDto>> $completion);
    
    @retrofit2.http.GET(value = "chat/task")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTaskByChatId(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "chatId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.chat.Task> $completion);
    
    @retrofit2.http.GET(value = "chat/task")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTaskByPubId(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "pubId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String pubId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.chat.Task> $completion);
    
    @retrofit2.http.GET(value = "tasks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTasks(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @retrofit2.http.Query(value = "userStatus")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userStatus, @retrofit2.http.Query(value = "taskStatus")
    @org.jetbrains.annotations.NotNull()
    java.lang.String taskStatus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.veyvolopayli.studhunter.domain.model.WideTask>> $completion);
    
    @retrofit2.http.POST(value = "reviews/new")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadReview(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest newReviewRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.review.ReviewDto> $completion);
}