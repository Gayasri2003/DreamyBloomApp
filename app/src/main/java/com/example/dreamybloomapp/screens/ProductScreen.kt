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
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.Product // Import Product data model
import com.example.dreamybloomapp.productData
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
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
            .height(220.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    // MASTER / DETAIL IMPLEMENTATION
                    navController.navigate(ScreenRoutes.ProductDetail.route.replace("{productId}", product.id.toString()))
                }
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.name,
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "LKR ${product.price}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /* Buy Now Action */ },
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Buy Now", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }
                    IconButton(
                        onClick = { /* Add to cart action */ },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Add to cart",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    DreamyBloomAppTheme {
        ProductScreen(navController = rememberNavController())
    }
}