package com.example.dreamybloomapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }

    // 2. Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800)
        )
        delay(1200L) // Hold time after animation


        // --- Navigate to Login ---
        navController.navigate(ScreenRoutes.Login.route) {
            popUpTo(ScreenRoutes.Splash.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- Animated Logo ---
        Box(
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 7.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                }
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dreamy_bloom_logo),
                contentDescription = "Dreamy Bloom App Logo",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Text(
            text = "DREAMY BLOOM",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )


    }
}


@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    DreamyBloomAppTheme {
        SplashScreen(navController = rememberNavController())
    }
}