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
fun RegisterForm(
    onRegister: (email: String, username: String, name: String, password: String, repeatPassword: String) -> Unit,
    onSwitchToLogin: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val isFormValid =
        email.isNotEmpty() && username.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        InputComposable(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "Masukkan email"
        )

        InputComposable(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            placeholder = "Masukkan username"
        )

        InputComposable(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            placeholder = "Masukkan nama lengkap"
        )

        InputComposable(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Masukkan password",
            isPassword = true
        )

        InputComposable(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = "Repeat Password",
            placeholder = "Ulangi password",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonComposable(
            text = "Register",
            onClick = { onRegister(email, username, name, password, repeatPassword) },
            enabled = isFormValid
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { onSwitchToLogin() }) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = TenanginTheme.colors.black
                        )
                    ) {
                        append("Sudah punya akun? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = TenanginTheme.colors.purple
                        )
                    ) {
                        append("Login sekarang!")
                    }
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


