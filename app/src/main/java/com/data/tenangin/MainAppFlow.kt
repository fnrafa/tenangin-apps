package com.data.tenangin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun MainAppFlow(context: Context) {
    var currentScreen by remember { mutableStateOf("auth") }

    when (currentScreen) {
        "preload" -> PreloadScreen(
            onNavigateToNext = {
                currentScreen = "splash"
            },
            context = context
        )

        "splash" -> SplashScreen(
            onNavigateToNext = {
                currentScreen = "onboarding"
            }
        )

        "onboarding" -> OnboardingScreen(
            onNavigateToNext = {
                currentScreen = "auth"
            }
        )

        "auth" -> AuthScreen(
            onNavigateToNext = {
            }
        )
    }
}


