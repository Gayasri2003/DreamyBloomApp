package com.example.dreamybloomapp.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppShell(rootNavController: NavController) {
    val bottomNavController = rememberNavController()
    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Products,
        BottomNavItem.Cart,
        BottomNavItem.Profile,
    )

    // Helper function to dynamically get the title for the TopAppBar
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentTitle = navItems.find { it.route == currentRoute }?.label ?: "Dreamy Bloom"


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = currentTitle) // Use the dynamic title
            })
        },
        bottomBar = { BottomNavigationBar(bottomNavController, navItems) }
    ) { paddingValues ->
        // This NavHost manages the content for the 4 tabs inside the Scaffold
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController = bottomNavController) }
            composable(BottomNavItem.Products.route) { ProductScreen() }
            composable(BottomNavItem.Cart.route) { CartScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Avoid building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when re-selecting
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}