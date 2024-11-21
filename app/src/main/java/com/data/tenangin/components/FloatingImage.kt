package com.data.tenangin.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FloatingImage(imageRes: Int, size: Dp) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -30f, targetValue = 30f, animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "Floating Animation"
    )

    val offsetX by infiniteTransition.animateFloat(
        initialValue = -20f, targetValue = 20f, animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "Floating Animation"
    )

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .offset(x = offsetX.dp, y = offsetY.dp)
            .size(size)
            .alpha(1f)
    )
}
