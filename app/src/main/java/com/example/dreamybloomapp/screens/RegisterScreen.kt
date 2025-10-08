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
import androidx.compose.ui.text.input.KeyboardType // Removing the KeyboardOptions import, but keeping this for clarity

// Note: The @OptIn annotation is necessary because TopAppBar is an experimental API.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    // State variables for form fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) } // Checkbox

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Login")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Use a Column with padding to contain the form elements
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 32.dp),
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

            // 2. Email Field (Standard Text Input - relying on a user to understand the format)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                // Keyboard type now defaults to text
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 3. Password Field (Password Input Type - masked)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(), // Masks input
                // Keyboard type now defaults to text
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


            // Register Button (No action on submission required for assignment)
            Button(
                onClick = { /* TODO: Navigate back to Login or main screen, if registration were successful */ },
                enabled = agreedToTerms,
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Register")
            }
        }
    }
}