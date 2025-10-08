package com.example.dreamybloomapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // 1. Setup Animatable state for scale
    val scale = remember { Animatable(0f) }

    // 2. Animation and Navigation Logic
    LaunchedEffect(key1 = true) {
        // Animation: Scale from 0.0f to 1.0f over 800ms
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
            )
        )
        delay(1200L) // Hold time after animation finishes (800ms + 1200ms = 2000ms total)
        navController.popBackStack()
        navController.navigate(ScreenRoutes.Login.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 3. Apply the scale animation using graphicsLayer
        Image(
            painter = painterResource(id = R.drawable.dreamy_bloom_logo),
            contentDescription = "Dreamy Bloom App Logo",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                } // Applied scaling animation
        )

        Text(
            text = "Dreamy Bloom",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}