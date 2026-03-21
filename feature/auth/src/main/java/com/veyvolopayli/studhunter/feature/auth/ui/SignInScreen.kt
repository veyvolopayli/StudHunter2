package com.veyvolopayli.studhunter.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veyvolopayli.studhunter.feature.auth.R
import com.veyvolopayli.studhunter.feature.auth.SignInEvent
import com.veyvolopayli.studhunter.feature.auth.SignInViewModel

private val Primary = Color(0xFF051367)
private val Secondary = Color(0xFF7D84AE)
private val Tertiary = Color(0xFFC5C8D9)
private val Background = Color(0xFFF5F5F5)
private val Surface = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1F1F1F)

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onSignedIn: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is SignInEvent.NavigateToMain -> onSignedIn()
                is SignInEvent.ShowError -> { /* state already carries errorMessage */ }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        // ── Top bar ──────────────────────────────────────────────────────────
        TopBar(title = "Авторизация", onBack = onBack)

        // ── Body ─────────────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 18.dp)
                .padding(top = 22.dp)
        ) {
            Text(
                text = "Пожалуйста, введите логин и пароль",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = TextPrimary,
                )
            )

            AuthTextField(
                modifier = Modifier.padding(top = 14.dp),
                value = state.username,
                onValueChange = viewModel::onUsernameChange,
                hint = "Логин",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            AuthTextField(
                modifier = Modifier.padding(top = 14.dp),
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                hint = "Пароль",
                isPassword = true,
                error = state.errorMessage,
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        if (!state.isLoading) viewModel.signIn()
                    }
                )
            )
        }

        // ── Bottom button ─────────────────────────────────────────────────────
        Button(
            onClick = {
                focusManager.clearFocus()
                viewModel.signIn()
            },
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                disabledContainerColor = Secondary,
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = Surface,
                    strokeWidth = 2.dp,
                )
            } else {
                Text(
                    text = "Войти",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
internal fun TopBar(title: String, onBack: () -> Unit) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Surface)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Назад",
                tint = TextPrimary,
                modifier = Modifier.size(22.dp),
            )
        }
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary,
            modifier = Modifier.padding(start = 10.dp),
        )
    }
}

@Composable
internal fun AuthTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isPassword: Boolean = false,
    error: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text(
                    text = hint,
                    color = Tertiary,
                    fontSize = 16.sp,
                )
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = TextPrimary,
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
                imeAction = imeAction,
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            isError = error != null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Surface,
                unfocusedContainerColor = Surface,
                errorContainerColor = Surface,
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Primary,
            )
        )
        if (error != null) {
            Text(
                text = error,
                color = Primary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp),
            )
        }
    }
}
