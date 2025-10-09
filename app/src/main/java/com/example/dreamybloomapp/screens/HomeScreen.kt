package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme

// --- FIX: Import Data Models and Lists from DataModels.kt ---
import com.example.dreamybloomapp.Product
import com.example.dreamybloomapp.SkinType
import com.example.dreamybloomapp.newArrivals
import com.example.dreamybloomapp.offers
import com.example.dreamybloomapp.skinTypes
import com.example.dreamybloomapp.mostPurchased
// -------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dreamy Bloom", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* Search Action */ }) { Icon(Icons.Filled.Search, contentDescription = "Search") }
                    IconButton(onClick = { /* Notifications Action */ }) { Icon(Icons.Filled.Notifications, contentDescription = "Notifications") }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 60.dp), // Padding for the fixed bottom nav bar
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Banner Section
            item {
                HomeBanner(
                    title = "Enhance Your Natural\nBeauty",
                    subtitle = "Shop Now",
                    imageRes = R.drawable.home_banner_img1_placeholder
                )
            }

            // 2. New Arrivals Section (Responsive Grid)
            item {
                ProductGridSection(
                    title = "New Arrivals",
                    products = newArrivals.take(6),
                    navController = navController
                )
            }

            // 3. Middle Promotional Banner
            item {
                MiddleBanner(
                    text1 = "The latest beauty and\nskin care products at\nyour fingertips",
                    text2 = "Revamp your\nbeauty routine!",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // 4. Offers Section (Horizontal Scroll)
            item {
                Text(
                    text = "Offers",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(offers) { offer ->
                        OfferCard(offerText = offer)
                    }
                }
            }

            // 5. Discover Products for Your Skin Type Section
            item {
                Text(
                    text = "Discover Products for\nYour Skin Type",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(skinTypes) { skinType ->
                        SkinTypeCard(skinType = skinType)
                    }
                }
            }

            // 6. Most Purchased Products Section (Responsive Grid)
            item {
                ProductGridSection(
                    title = "Most Purchased Products",
                    products = mostPurchased.take(6),
                    navController = navController
                )
            }

            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

// --- Composable Components ---

@Composable
fun HomeBanner(title: String, subtitle: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Button(
                    onClick = { /* Shop Now Action */ },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(subtitle, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
fun ProductGridSection(title: String, products: List<Product>, navController: NavController) {
    // Responsiveness Logic: Determine column count based on screen width
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    // RESPONSIVENESS: Switches to 3 columns in landscape/tablet
    val columnCount = when {
        screenWidthDp >= 600.dp -> 3
        screenWidthDp > 400.dp -> 2
        else -> 2
    }

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            products.chunked(columnCount).forEach { rowProducts ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowProducts.forEach { product ->
                        ProductCard(product = product, navController = navController, modifier = Modifier.weight(1f).padding(horizontal = 4.dp))
                    }
                    repeat(columnCount - rowProducts.size) {
                        Spacer(modifier = Modifier.weight(1f).padding(horizontal = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavController, modifier: Modifier = Modifier) {
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
                    // MASTER / DETAIL IMPLEMENTATION: Placeholder navigation
                    navController.navigate(ScreenRoutes.Product.route)
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

@Composable
fun MiddleBanner(text1: String, text2: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = text2,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxHeight()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun OfferCard(offerText: String) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.product_img_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(offerText, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                Button(
                    onClick = { /* View Offer Action */ },
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Buy Now", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}

@Composable
fun SkinTypeCard(skinType: SkinType) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(130.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = skinType.imageRes),
                contentDescription = skinType.name,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(skinType.name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                Icon(Icons.Filled.ArrowForward, contentDescription = "Go", modifier = Modifier.size(12.dp), tint = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DreamyBloomAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}