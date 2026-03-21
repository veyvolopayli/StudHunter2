package com.veyvolopayli.studhunter.feature.auth.ui;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import com.veyvolopayli.studhunter.feature.auth.R;
import com.veyvolopayli.studhunter.feature.auth.SignInEvent;
import com.veyvolopayli.studhunter.feature.auth.SignInViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aj\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a,\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u001eH\u0007\u001a\u001e\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\r2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u001eH\u0001\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\""}, d2 = {"Background", "Landroidx/compose/ui/graphics/Color;", "J", "Primary", "Secondary", "Surface", "Tertiary", "TextPrimary", "AuthTextField", "", "modifier", "Landroidx/compose/ui/Modifier;", "value", "", "onValueChange", "Lkotlin/Function1;", "hint", "isPassword", "", "error", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "AuthTextField-2T9HftM", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;ZLjava/lang/String;ILandroidx/compose/foundation/text/KeyboardActions;)V", "SignInScreen", "viewModel", "Lcom/veyvolopayli/studhunter/feature/auth/SignInViewModel;", "onSignedIn", "Lkotlin/Function0;", "onBack", "TopBar", "title", "auth_debug"})
public final class SignInScreenKt {
    private static final long Primary = 0L;
    private static final long Secondary = 0L;
    private static final long Tertiary = 0L;
    private static final long Background = 0L;
    private static final long Surface = 0L;
    private static final long TextPrimary = 0L;
    
    @androidx.compose.runtime.Composable()
    public static final void SignInScreen(@org.jetbrains.annotations.NotNull()
    com.veyvolopayli.studhunter.feature.auth.SignInViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSignedIn, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TopBar(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
}