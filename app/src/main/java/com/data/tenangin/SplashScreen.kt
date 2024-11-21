package com.data.tenangin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToNext: () -> Unit
) {
    var showFinalText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showFinalText = true
        delay(4000)
        onNavigateToNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        LoadingAnimation()
        if (showFinalText) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(2000)) + scaleIn(initialScale = 0.8f),
                exit = fadeOut()
            ) {
                FinalText()
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    val animatedSize = remember { Animatable(1500f) }

    LaunchedEffect(Unit) {
        animatedSize.animateTo(
            targetValue = 2500f, animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
        delay(1500)
        animatedSize.animateTo(
            targetValue = 1500f, animationSpec = tween(durationMillis = 400, easing = LinearEasing)
        )
        animatedSize.animateTo(
            targetValue = 2000f, animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
        animatedSize.animateTo(
            targetValue = 1500f, animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
        animatedSize.animateTo(
            targetValue = 8000f, animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    Box(
        modifier = Modifier
            .offset(x = (-300).dp, y = 250.dp)
            .size(animatedSize.value.dp)
            .graphicsLayer(
                scaleX = animatedSize.value / 600f, scaleY = animatedSize.value / 600f
            ), contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.flower),
            contentDescription = "Loading Animation",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FinalText() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tenangin),
            contentDescription = "Title Tenangin",
            modifier = Modifier.fillMaxSize()
        )
    }
}
