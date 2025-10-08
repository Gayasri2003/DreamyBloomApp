package com.example.dreamybloomapp.navigation

sealed class ScreenRoutes(val route: String) {
    // Initial Screens
    object Splash : ScreenRoutes("splash_screen")
    object Login : ScreenRoutes("login_screen")
    object Register : ScreenRoutes("register_screen")

    // Main Screens (Accessed via bottom/top navigation bar)
    object Home : ScreenRoutes("home_screen")
    object Product : ScreenRoutes("product_screen")
    object Cart : ScreenRoutes("cart_screen")
    object Profile : ScreenRoutes("profile_screen")

    }