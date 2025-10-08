
package com.example.dreamybloomapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.screens.*
import com.example.dreamybloomapp.navigation.MainAppShell


@Composable
fun AppNavigation() {
    // 1. Initialize the NavController, which manages the navigation stack
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Splash.route
    ) {
        // Initial Screens - Must pass navController so they can navigate away
        composable(ScreenRoutes.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(ScreenRoutes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(ScreenRoutes.Register.route) {
            RegisterScreen(navController = navController)
        }

        // Main App Shell - This is the destination after Login.
        // It contains the fixed Bottom Navigation Bar and handles the 4 inner screens.
        composable(ScreenRoutes.Home.route) {
            MainAppShell(rootNavController = navController)
        }

        // NOTE: The individual Composables (HomeScreen, ProductScreen, etc.)
        // are now called *inside* MainAppShell.kt, not here.
    }
}