package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- Logo ---
        Box(
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 7.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dreamy_bloom_logo),
                contentDescription = "Dreamy Bloom Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()

            )
        }


        Text(
            text = "WELCOME BACK",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 24.dp)

        )

        // --- 1. Email/Username Field ---
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email or Username") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // --- 2. Password Field ---
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- 3. Login Button ---
        Button(
            onClick = { navController.navigate(ScreenRoutes.Home.route) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "LOG IN",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        // --- Register Link  ---
        TextButton(
            onClick = { navController.navigate(ScreenRoutes.Register.route) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                "Don't have an account? Register here",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DreamyBloomAppTheme {
        LoginScreen(navController = rememberNavController())
    }
}