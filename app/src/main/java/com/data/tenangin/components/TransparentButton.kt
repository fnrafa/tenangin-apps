package com.data.tenangin.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TransparentButton(
    onClick: () -> Unit, enabled: Boolean, text: String
) {
    Button(
        onClick = { if (enabled) onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp),
        enabled = enabled
    ) {
        Text(text)
    }
}
