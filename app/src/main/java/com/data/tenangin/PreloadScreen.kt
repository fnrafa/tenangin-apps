package com.data.tenangin

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.data.tenangin.components.AlertDialogComposable
import com.data.tenangin.utils.ApiClient
import com.data.tenangin.utils.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess

@Composable
fun PreloadScreen(
    onNavigateToNext: () -> Unit, context: Context
) {
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (!isInternetAvailable(context)) {
            errorMessage =
                "Tidak ada koneksi internet. Pastikan perangkat Anda terhubung ke internet."
            showErrorDialog = true
        } else {
            val isServerActive = withContext(Dispatchers.IO) {
                try {
                    ApiClient.apiService.pingServer()
                    true
                } catch (e: Exception) {
                    errorMessage = "Tidak dapat terhubung dengan server."
                    false
                }
            }

            if (!isServerActive) {
                showErrorDialog = true
            } else {
                onNavigateToNext()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        if (showErrorDialog) {
            AlertDialogComposable(
                title = "Error", message = errorMessage, onDismiss = {
                    showErrorDialog = false
                    exitProcess(1)
                }, dismissButtonLabel = "Ok"
            )
        }
    }
}
