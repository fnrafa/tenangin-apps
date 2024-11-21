package com.data.tenangin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.tenangin.components.AlertDialogComposable
import com.data.tenangin.components.FloatingImage
import com.data.tenangin.components.LoginForm
import com.data.tenangin.components.RegisterForm
import com.data.tenangin.ui.theme.TenanginTheme
import com.data.tenangin.utils.ApiClient
import com.data.tenangin.utils.ApiClient.parseErrorMessage
import com.data.tenangin.utils.LoginRequest
import com.data.tenangin.utils.RegisterRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(onNavigateToNext: () -> Unit) {
    val pages = listOf(
        PageContent(
            title = "Ungkapkan Perasaan",
            content = "Bicara Kesehatan Mental Tanpa Rasa Malu",
            backgroundColor = TenanginTheme.colors.purple,
            background = R.drawable.leaf1,
            topImage = R.drawable.people1
        ), PageContent(
            title = "Bicarakan & Suarakan",
            content = "Bebas dari Perasaan Khawatir dan Penilaian",
            backgroundColor = TenanginTheme.colors.orange,
            background = R.drawable.leaf2,
            topImage = R.drawable.people2
        ), PageContent(
            title = "Nyatakan Perasaanmu",
            content = "Bicara Kesehatan Mental, Bebas Dari Stigma",
            backgroundColor = TenanginTheme.colors.green,
            background = R.drawable.leaf3,
            topImage = R.drawable.people3
        )
    )

    var currentPage by remember { mutableStateOf(0) }
    var isLoginMode by remember { mutableStateOf(true) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentPage = (currentPage + 1) % pages.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = pages[currentPage].backgroundColor)
    ) {
        FloatingImage(imageRes = pages[currentPage].background, size = 1000.dp)
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp)
                .padding(16.dp)
        ) {
            Text(
                text = pages[currentPage].title,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = TenanginTheme.colors.white
            )
            Text(
                text = pages[currentPage].content,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = TenanginTheme.colors.white
            )
        }
        Image(
            painter = painterResource(id = pages[currentPage].topImage),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .offset(x = 100.dp, y = 200.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(36.dp))
                .background(TenanginTheme.colors.cream)
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.tenangin),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Divider(
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (isLoginMode) {
                LoginForm(onLogin = { username, password ->
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            val response = ApiClient.apiService.login(
                                LoginRequest(username, password)
                            )
                            if (response.isSuccessful) {
                                onNavigateToNext()
                            } else {
                                errorMessage = parseErrorMessage(response)
                                showErrorDialog = true
                            }
                        } catch (e: Exception) {
                            errorMessage = e.message ?: "An error occurred"
                            showErrorDialog = true
                        } finally {
                            isLoading = false
                        }
                    }
                }, onSwitchToRegister = { isLoginMode = false })
            } else {
                RegisterForm(onRegister = { email, username, name, password, repeatPassword ->
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            val response = ApiClient.apiService.register(
                                RegisterRequest(
                                    email, username, name, password, repeatPassword
                                )
                            )
                            if (response.isSuccessful) {
                                onNavigateToNext()
                            } else {
                                errorMessage = parseErrorMessage(response)
                                showErrorDialog = true
                            }
                        } catch (e: Exception) {
                            errorMessage = e.message ?: "An error occurred"
                            showErrorDialog = true
                        } finally {
                            isLoading = false
                        }
                    }
                }, onSwitchToLogin = { isLoginMode = true })
            }
        }

        if (showErrorDialog) {
            AlertDialogComposable(
                onDismiss = { showErrorDialog = false },
                title = "Error",
                message = errorMessage,
                confirmButtonLabel = "Ok"
            )
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = TenanginTheme.colors.darkPurple)
            }
        }
    }
}

data class PageContent(
    val title: String,
    val content: String,
    val backgroundColor: Color,
    val background: Int,
    val topImage: Int,
)

