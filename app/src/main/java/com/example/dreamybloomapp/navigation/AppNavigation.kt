package com.example.dreamybloomapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dreamybloomapp.screens.*


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Splash.route
    ) {
        // --- Initial Screens ---
        composable(ScreenRoutes.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(ScreenRoutes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(ScreenRoutes.Register.route) {
            RegisterScreen(navController = navController)
        }

        // --- Main App Shell (Hosts the bottom navigation) ---
        composable(ScreenRoutes.Home.route) {
            MainAppShell(rootNavController = navController)
        }

        // --- Define the Product Detail Route ---
        composable(
            route = ScreenRoutes.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            ProductDetailScreen(
                navController = navController,
                productId = backStackEntry.arguments?.getInt("productId") ?: 0
            )
        }
    }
}