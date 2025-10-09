package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.dreamybloomapp.Product // Import Product data model
import com.example.dreamybloomapp.productData

// --- Data Models (Assume Product is imported from DataModels.kt) ---
// Note: productData needs to be available either through DataModels.kt or defined here.
// Assuming it is defined in DataModels.kt as per previous instruction.
// val productData = mapOf(...)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .padding(bottom = 60.dp) // Pushes content above the FIXED Bottom Navigation Bar
    ) {
        item {
            // Custom Top Bar Area (Search/Back)
            ProductTopBar(searchQuery = searchQuery, onSearchChange = { searchQuery = it }, navController = navController)

            // Sort Selector (Simplified)
            Text(
                text = "Sort by: Featured",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Divider(color = MaterialTheme.colorScheme.outlineVariant)
        }

        // Product Grid Sections (5 sections required)
        productData.forEach { (title, products) ->
            item {
                ProductSectionHeader(title)
                // Passing the first 4 products for a responsive grid view
                ProductResponsiveGrid(products = products.take(4), navController = navController)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }
    }
}

// --- Custom Components ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar(searchQuery: String, onSearchChange: (String) -> Unit, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Arrow Navigation to previous screen (Home)
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Home", tint = MaterialTheme.colorScheme.onBackground)
        }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Search Products...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.weight(1f).height(56.dp).padding(horizontal = 8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        )
        IconButton(onClick = { /* Navigate to Notifications */ }) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun ProductSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

// --- Responsive Grid Component (Implements 2 distinct layouts) ---

@Composable
fun ProductResponsiveGrid(products: List<Product>, navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    // RESPONSIVENESS LOGIC: 2 columns for Portrait, 3 for Landscape (Assignment Requirement)
    val columnCount = when {
        screenWidthDp >= 600.dp -> 3
        else -> 2
    }

    Column {
        products.chunked(columnCount).forEach { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                rowProducts.forEach { product ->
                    ProductGridCard(
                        product = product,
                        navController = navController,
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp, vertical = 8.dp)
                    )
                }
                // Fill remaining space if the last row is incomplete
                repeat(columnCount - rowProducts.size) {
                    Spacer(modifier = Modifier.weight(1f).padding(horizontal = 4.dp))
                }
            }
        }
    }
}

// --- Individual Product Card (Master Item) ---

@Composable
fun ProductGridCard(product: Product, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(260.dp)
            .clickable {
                // FIX: Navigate to Cart page as a temporary Master/Detail interaction
                navController.navigate(ScreenRoutes.Cart.route)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section (Image and Wishlist Icon)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                )
                IconButton(onClick = { /* Wishlist Action */ }, modifier = Modifier.size(28.dp)) {
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = "Wishlist",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Middle Section (Name and Price)
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Bottom Section (Price and Add to Cart)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("LKR", style = MaterialTheme.typography.labelSmall)
                    Text(
                        product.price,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Add to Cart Quantity Button
                OutlinedTextField(
                    value = "1",
                    onValueChange = { /* Update Quantity */ },
                    modifier = Modifier.width(40.dp).height(32.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, textAlign = TextAlign.Center),
                       )
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = { /* Add to Cart Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Add to Cart", fontSize = 10.sp)
                }
            }
        }
    }
}