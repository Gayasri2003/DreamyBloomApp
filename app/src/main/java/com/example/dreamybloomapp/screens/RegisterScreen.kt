package com.example.dreamybloomapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll // Needed for responsiveness
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    // State variables for form fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) } // Checkbox
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
                navigationIcon = {
                    IconButton(
                        // --- FIX 1: Navigate to Splash screen ---
                        onClick = {
                            navController.navigate(ScreenRoutes.Splash.route) {
                                // Clear back stack to start new flow
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Splash")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState), // Added scroll for responsiveness
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Join Dreamy Bloom",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 1. Full Name Field (Standard Text Input)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 2. Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 3. Password Field (Password Input Type - masked)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(), // Masks input
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 4. Checkbox (Required additional component)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it }
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "I agree to the terms and conditions",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.weight(1f)
                )
            }


            // Register Button
            Button(
                // --- FIX 2: Navigate to Login screen after registration ---
                onClick = { navController.navigate(ScreenRoutes.Login.route) },
                enabled = agreedToTerms,
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Register")
            }
        }
    }
}

// Preview remains the same
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    DreamyBloomAppTheme {
        RegisterScreen(navController = rememberNavController())
    }
}