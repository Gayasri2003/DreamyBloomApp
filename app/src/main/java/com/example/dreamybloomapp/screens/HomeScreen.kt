package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.unit.sp
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme
import androidx.compose.material3.ExperimentalMaterial3Api

// --- Data Models  ---
data class Product(val id: Int, val name: String, val price: String, val imageRes: Int)
data class SkinType(val id: Int, val name: String, val imageRes: Int)

// Dummy Data
val newArrivals = listOf(
    Product(1, "Lip Balm", "$12.99", R.drawable.product_img_lipbalm),
    Product(2, "Face Wash", "$18.50", R.drawable.product_img_facewash),
    Product(3, "Body Milk", "$25.00", R.drawable.product_img_bodymilk),
    Product(4, "Lotion Oil", "$30.75", R.drawable.product_img_lotionoil),
    Product(5, "Toner Mist", "$15.90", R.drawable.product_img_toner),
    Product(6, "Face Serum", "$45.00", R.drawable.product_img_facecerum),
)
val offers = listOf(
    "Offer 1", "Offer 2", "Offer 3", "Offer 4"
)
val skinTypes = listOf(
    SkinType(1, "Oily Skin", R.drawable.skintype_oilyskin),
    SkinType(2, "Dry Skin", R.drawable.skintype_dryskin),
    SkinType(3, "Sensitive Skin", R.drawable.skintype_sensitiveskin),
)
val mostPurchased = listOf(
    Product(10, "Daily Cream", "$22.00", R.drawable.product_img_dailycream),
    Product(11, "Face Cream", "$35.00", R.drawable.product_img_facilcream),
    Product(12, "Oil Serum", "$48.00", R.drawable.product_img_oilserum),
)
// ------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dreamy Bloom",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Navigate to search */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* TODO: Navigate to notifications */ }) {
                        // Using Material Icons for the bell/notifications
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = { /* TODO: Navigate to cart */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        },
        bottomBar = {
            // Re-adding the simple BottomNavigationBar as requested
            BottomNavigationBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 1. Banner Section
            item {
                HomeBanner(
                    title = "Enhance Your Natural Beauty",
                    subtitle = "Shop Now",
                    imageRes = R.drawable.home_banner_img1_placeholder
                )
            }

            // 2. New Arrivals Section
            item {
                ProductGridSection(
                    title = "New Arrivals",
                    products = newArrivals.take(6)
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

            // 6. Most Purchased Products Section (2 products per row, up to 3 rows)
            item {
                ProductGridSection(
                    title = "Most Purchased Products",
                    products = mostPurchased.take(6) // Take first 6 products for 3 rows
                )
            }

            // Final Spacer for bottom bar clearance
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

// --- Composable Components (Unchanged or minor adjustments for images/icons) ---

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
            // Note: R.drawable.home_banner_img1_placeholder must exist in your project
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
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Button(
                    onClick = { /* Shop Now Action */ },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                ) {
                    Text(subtitle, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ProductGridSection(title: String, products: List<Product>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            products.chunked(2).forEachIndexed { index, rowProducts ->
                if (index < 3) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowProducts.forEach { product ->
                            ProductCard(product = product, modifier = Modifier.weight(1f).padding(horizontal = 4.dp))
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f).padding(horizontal = 4.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                    .background(Color(0xFFFCE4EC)),
                contentAlignment = Alignment.Center
            ) {
                // Note: R.drawable.product_img_placeholder must exist in your project
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Buy Now Action */ },
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                ) {
                    Text("Buy Now", fontSize = 10.sp)
                }
                IconButton(
                    onClick = { /* Add to cart action */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Add to cart",
                        tint = Color.Black
                    )
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
            .background(Color(0xFFF8BBD0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = text2,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFE91E63))
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC))
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
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(offerText, fontWeight = FontWeight.Medium, fontSize = 12.sp)
                Button(
                    onClick = { /* View Offer Action */ },
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                ) {
                    Text("Buy Now", fontSize = 10.sp)
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Note: R.drawable.skintype_placeholder must exist in your project
            Image(
                painter = painterResource(id = skinType.imageRes),
                contentDescription = skinType.name,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(skinType.name, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Icon(Icons.Filled.ArrowForward, contentDescription = "Go", modifier = Modifier.size(12.dp))
            }
        }
    }
}


@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        val items = listOf(
            Triple("Home", Icons.Filled.Home, 0),
            Triple("Products", Icons.Filled.Star, 1),
            Triple("Cart", Icons.Filled.ShoppingCart, 2),
            Triple("Profile", Icons.Filled.AccountCircle, 3)
        )
        val selectedIndex = 0 // Assuming 'Home' is selected

        items.forEach { (label, icon, index) ->
            NavigationBarItem(
                icon = {
                    Icon(
                        icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(label) },
                selected = index == selectedIndex,
                onClick = { /* Handle navigation */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DreamyBloomAppTheme {
        HomeScreen()
    }
}