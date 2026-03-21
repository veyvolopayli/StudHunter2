package com.veyvolopayli.studhunter.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veyvolopayli.studhunter.feature.auth.SignUpEvent
import com.veyvolopayli.studhunter.feature.auth.SignUpViewModel

private val Primary = Color(0xFF051367)
private val Secondary = Color(0xFF7D84AE)
private val Background = Color(0xFFF5F5F5)
private val Surface = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1F1F1F)

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onSignedUp: () -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is SignUpEvent.NavigateToMain -> onSignedUp()
                is SignUpEvent.ShowError -> { /* errors shown inline */ }
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
        TopBar(title = "Регистрация", onBack = onBack)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
                .padding(top = 22.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Укажите данные для регистрации",
                style = TextStyle(fontSize = 16.sp, color = TextPrimary),
            )

            AuthTextField(
                modifier = Modifier.padding(top = 14.dp),
                value = state.username,
                onValueChange = viewModel::onUsernameChange,
                hint = "Никнейм",
                error = state.usernameError,
                imeAction = ImeAction.Next,
            )
            AuthTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                hint = "Пароль",
                isPassword = true,
                error = state.passwordError,
                imeAction = ImeAction.Next,
            )
            AuthTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = state.email,
                onValueChange = viewModel::onEmailChange,
                hint = "Почта",
                error = state.emailError,
                imeAction = ImeAction.Next,
            )
            AuthTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = state.name,
                onValueChange = viewModel::onNameChange,
                hint = "Имя",
                error = state.nameError,
                imeAction = ImeAction.Next,
            )
            AuthTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = state.surname,
                onValueChange = viewModel::onSurnameChange,
                hint = "Фамилия",
                error = state.surnameError,
                imeAction = ImeAction.Next,
            )
            AuthTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = state.university,
                onValueChange = viewModel::onUniversityChange,
                hint = "Университет",
                error = state.universityError,
                imeAction = ImeAction.Done,
            )
        }

        Button(
            onClick = {
                focusManager.clearFocus()
                viewModel.trySignUp()
            },
            enabled = !state.isLoading,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                disabledContainerColor = Secondary,
            ),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = Surface,
                    strokeWidth = 2.dp,
                )
            } else {
                Text(
                    text = "Зарегистрироваться",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        }
    }
}
