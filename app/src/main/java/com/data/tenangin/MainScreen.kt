package com.data.tenangin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.core.view.WindowCompat
import com.data.tenangin.ui.theme.TenanginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TenanginTheme {
                AntialiasedWrapper {
                    MainAppFlow(context = this)
                }
            }
        }
    }
}

@Composable
fun AntialiasedWrapper(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.graphicsLayer(
            clip = false,
            alpha = 0.99f
        )
    ) {
        content()
    }
}
