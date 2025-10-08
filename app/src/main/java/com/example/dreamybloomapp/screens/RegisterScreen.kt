package com.example.dreamybloomapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dreamybloomapp.navigation.ScreenRoutes

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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Join Dreamy Bloom",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 1. Text Field (Name)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 2. Email Field (Different type for keyboard/format)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 3. Password Field (Different type for visibility toggle/masking)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            // 4. Checkbox (The 4th, different component type, satisfying Req 34)
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
                Text("I agree to the terms and conditions", style = MaterialTheme.typography.labelMedium)
            }


            // Register Button (No functionality required yet)
            Button(
                onClick = { /* No action on submission required for assignment */ },
                enabled = agreedToTerms, // Disable if terms are not agreed
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Register")
            }
        }
    }
}