package com.data.tenangin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.tenangin.components.FloatingImage
import com.data.tenangin.components.FullScreenBackground
import com.data.tenangin.components.TransparentButton
import com.data.tenangin.ui.theme.TenanginTheme


@Composable
fun OnboardingScreen(onNavigateToNext: () -> Unit) {
    var page by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (page) {
                    0 -> TenanginTheme.colors.yellow
                    1 -> TenanginTheme.colors.purple
                    else -> TenanginTheme.colors.pink
                }
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        FullScreenBackground(
            imageRes = when (page) {
                0 -> R.drawable.doodle3
                1 -> R.drawable.doodle1
                else -> R.drawable.doodle2
            }
        )

        Box(
            modifier = Modifier.padding(0.dp, 200.dp),
        ) {
            FloatingImage(
                imageRes = when (page) {
                    0 -> R.drawable.tiger
                    1 -> R.drawable.unicorn
                    else -> R.drawable.pig
                },
                size = 500.dp,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(36.dp, 36.dp))
                .background(Color.White)
                .padding(bottom = 36.dp)
                .padding(16.dp),
        ) {
            Text(
                text = when (page) {
                    0 -> "Selamat Datang di"
                    1 -> "Kebahagiaan Adalah Hak"
                    else -> "Bebaskan Pikiran"
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(20.dp, 0.dp),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(20.dp, 0.dp),
                contentAlignment = Alignment.TopStart
            ) {
                if (page == 0) {
                    Image(
                        painter = painterResource(id = R.drawable.tenangin),
                        contentDescription = "Title Tenangin",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(top = 10.dp),
                        alignment = Alignment.TopStart
                    )
                } else {
                    Text(
                        text = when (page) {
                            1 -> "Semua Orang"
                            else -> "Raih Ketenangan"
                        },
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TransparentButton(
                    onClick = { if (page > 0) page-- },
                    enabled = page > 0,
                    text = if (page > 0) "Prev" else ""
                )
                TransparentButton(
                    onClick = {
                        if (page < 2) {
                            page++
                        } else {
                            onNavigateToNext()
                        }
                    },
                    enabled = true,
                    text = if (page < 2) "Next" else "Finish"
                )
            }
        }
    }
}

