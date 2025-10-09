package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme

// Data model for menu items
data class ProfileMenu(val label: String, val icon: ImageVector, val route: String)

// Dummy User Info
val userName = "Darlene Robertson"
val userEmail = "darlene.robertson@example.com"
val profileImageRes = R.drawable.profile_placeholder
// Menu Items
val profileMenuItems = listOf(
    ProfileMenu("Edit Profile", Icons.Filled.Person, "edit_profile_route"),
    ProfileMenu("Shopping Address", Icons.Filled.LocationOn, "shipping_address_route"),
    ProfileMenu("Wishlist", Icons.Filled.Favorite, "wishlist_route"),
    ProfileMenu("Order History", Icons.Filled.Receipt, ScreenRoutes.Home.route),
    ProfileMenu("Notifications", Icons.Filled.Notifications, "notifications_route"),
    ProfileMenu("Cards", Icons.Filled.CreditCard, "cards_route"),
)

@Composable
fun ProfileScreen(navController: NavController) {
    LazyColumn(
        // Ensure content scrolls and has correct padding above the fixed bottom bar
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // --- Custom Top Bar for Back Navigation and Title Spacing ---
            ProfileTopBar(navController = navController, title = "Profile")

            // --- 1. Profile Picture and Status ---
            Spacer(modifier = Modifier.height(24.dp))
            ProfilePictureAndStatus()
            Spacer(modifier = Modifier.height(32.dp))

            // --- 2. Menu Items ---
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                profileMenuItems.forEach { item ->
                    ProfileMenuItem(item = item, navController = navController)
                    Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 0.5.dp)
                }
            }

            // --- 3. Logout Button (If required, not in mockup, but essential for navigation flow) ---
            // Removed for strict mockup compliance. You can add it back if needed.
        }
    }
}

// --- Component Composables ---

@Composable
fun ProfileTopBar(navController: NavController, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Back Arrow Navigation to Home Page
        IconButton(onClick = { navController.navigate(ScreenRoutes.Home.route) }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Home", tint = MaterialTheme.colorScheme.onBackground)
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ProfilePictureAndStatus() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Profile Picture with Circles (Mimicking the mockup's circular layering)
        Box(
            modifier = Modifier
                .size(130.dp)
                .background(Color.Transparent, CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape), // Outer circle border
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = "User Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape) // Inner white border effect
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = userName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Active Status (Mimicking the small dot/text)
        Text(
            text = "● Active status",
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF4CAF50), // Standard green color for active status
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ProfileMenuItem(item: ProfileMenu, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(item.route) }
            .padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary, // Primary color for icon
                modifier = Modifier.size(24.dp).padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                item.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Icon(
            Icons.Filled.ArrowForwardIos,
            contentDescription = "Go",
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DreamyBloomAppTheme {
        ProfileScreen(navController = rememberNavController())
    }
}