package com.data.tenangin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun FullScreenBackground(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Doodle Fullscreen Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
        )
    }
}
