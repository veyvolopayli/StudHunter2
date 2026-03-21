package com.veyvolopayli.studhunter.data.repository;

import com.veyvolopayli.studhunter.data.remote.StudHunterApi;
import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest;
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto;
import com.veyvolopayli.studhunter.domain.repository.ReviewsRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/ReviewsRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/ReviewsRepository;", "api", "Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;", "(Lcom/veyvolopayli/studhunter/data/remote/StudHunterApi;)V", "uploadReview", "Lcom/veyvolopayli/studhunter/domain/model/review/ReviewDto;", "token", "", "newReviewRequest", "Lcom/veyvolopayli/studhunter/domain/model/review/NewReviewRequest;", "(Ljava/lang/String;Lcom/veyvolopayli/studhunter/domain/model/review/NewReviewRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class ReviewsRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.ReviewsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.veyvolopayli.studhunter.data.remote.StudHunterApi api = null;
    
    @javax.inject.Inject()
    public ReviewsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.data.remote.StudHunterApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadReview(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest newReviewRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.veyvolopayli.studhunter.domain.model.review.ReviewDto> $completion) {
        return null;
    }
}