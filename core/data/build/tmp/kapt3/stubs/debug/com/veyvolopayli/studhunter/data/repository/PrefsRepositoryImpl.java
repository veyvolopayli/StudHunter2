package com.veyvolopayli.studhunter.data.repository;

import android.content.SharedPreferences;
import com.veyvolopayli.studhunter.common.Constants;
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/veyvolopayli/studhunter/data/repository/PrefsRepositoryImpl;", "Lcom/veyvolopayli/studhunter/domain/repository/PrefsRepository;", "prefs", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "clearPrefs", "", "getJwtToken", "", "insertJwtToken", "token", "data_debug"})
public final class PrefsRepositoryImpl implements com.veyvolopayli.studhunter.domain.repository.PrefsRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    
    @javax.inject.Inject()
    public PrefsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getJwtToken() {
        return null;
    }
    
    @java.lang.Override()
    public void insertJwtToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void clearPrefs() {
    }
}