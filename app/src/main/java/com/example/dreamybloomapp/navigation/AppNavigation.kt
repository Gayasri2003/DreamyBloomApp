package com.example.dreamybloomapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType // Required for argument type
import androidx.navigation.navArgument // Required for defining arguments
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

        // --- MASTER/DETAIL: Define the Product Detail Route ---
        composable(
            // The route template expects an integer ID (e.g., "product_detail/12")
            route = ScreenRoutes.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            // Instantiate the Detail Screen, extracting the ID from the arguments
            ProductDetailScreen(
                navController = navController,
                productId = backStackEntry.arguments?.getInt("productId") ?: 0
            )
        }
    }
}