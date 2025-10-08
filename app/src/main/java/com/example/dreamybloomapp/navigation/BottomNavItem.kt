package com.example.dreamybloomapp.navigation

import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person


// Sealed class definition starts here
sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(ScreenRoutes.Home.route, "Home", Icons.Filled.Home)
    object Products : BottomNavItem(ScreenRoutes.Product.route, "Products", Icons.Filled.LocalMall)
    object Cart : BottomNavItem(ScreenRoutes.Cart.route, "Cart", Icons.Filled.ShoppingCart)
    object Profile : BottomNavItem(ScreenRoutes.Profile.route, "Profile", Icons.Filled.Person)
}