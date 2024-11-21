package com.data.tenangin.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.tenangin.ui.theme.TenanginTheme

@Composable
fun LoginForm(
    onLogin: (username: String, password: String) -> Unit,
    onSwitchToRegister: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isFormValid = username.isNotEmpty() && password.isNotEmpty()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        InputComposable(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            placeholder = "Masukkan username"
        )

        InputComposable(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Masukkan password",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonComposable(
            text = "Login",
            onClick = { onLogin(username, password) },
            enabled = isFormValid
        )

        Spacer(modifier = Modifier.height(4.dp))

        TextButton(onClick = { onSwitchToRegister() }) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = TenanginTheme.colors.black
                        )
                    ) {
                        append("Belum punya akun? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = TenanginTheme.colors.darkPurple
                        )
                    ) {
                        append("Daftar sekarang!")
                    }
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

