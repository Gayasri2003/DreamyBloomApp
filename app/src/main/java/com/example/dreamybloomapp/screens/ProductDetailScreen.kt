package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.Product
import com.example.dreamybloomapp.productData
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme


val FallbackProduct = Product(
    id = 0,
    name = "Product Not Found",
    price = "0.00",
    imageRes = R.drawable.product_img_placeholder,
    category = "N/A",
    description = "The requested product could not be found. Please return to the product listing."
)

fun getProductById(id: Int): Product? {
    return productData.values.flatten().find { it.id == id }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: Int) {
    // Attempt to find the product
    val product: Product = getProductById(productId) ?: FallbackProduct

    // If product is null
    if (productId == 0) {
        Scaffold(
            topBar = { DetailTopBar(navController = navController, title = "Product Not Found") }
        ) { paddingValues ->
            Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("Error: Product with ID 0 not found.", color = MaterialTheme.colorScheme.error)
            }
        }
        return
    }

    Scaffold(
        topBar = { DetailTopBar(navController = navController, title = product.name) },
        // Fixed bottom bar for Add to Cart action
        bottomBar = { DetailBottomBar(product = product) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(16.dp)
        ) {
            // 1. Large Image Display
            item {
                ProductImageSection(product = product)
            }

            // 2. Product Name, Price, and Category
            item {
                ProductInfoSection(product = product)
            }

            // 3. Description
            item {
                ProductDescriptionSection()
            }

            // 4. Specs/Reviews (Placeholder section)
            item {
                ProductReviewSection()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavController, title: String) {
    TopAppBar(
        title = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            // Navigation: Pop back to the previous screen
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }
        },
        actions = {
            IconButton(onClick = { /* Add to Wishlist */ }) {
                Icon(Icons.Filled.FavoriteBorder, contentDescription = "Wishlist")
            }
        }
    )
}

@Composable
fun ProductImageSection(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize().padding(32.dp)
        )
    }
}

@Composable
fun ProductInfoSection(product: Product) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Category: ${product.category}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "LKR ${product.price}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Divider(modifier = Modifier.padding(vertical = 16.dp))
    }
}

@Composable
fun ProductDescriptionSection() {
    Text(
        text = "Product Details",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "This luxurious formulation provides deep hydration and a radiant finish, enriched with natural oils and SPF 30. Ideal for daily use on all skin types, especially dry and sensitive skin. Experience the natural glow, long-lasting wear, and flawless coverage.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun ProductReviewSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Customer Reviews (4.5)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            TextButton(onClick = { /* View All Reviews */ }) {
                Text("View All")
            }
        }
        Text("No reviews yet. Be the first!", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun DetailBottomBar(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Total: LKR ${product.price}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { /* Add to Cart Action */ },
            modifier = Modifier.width(180.dp).fillMaxHeight(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Icon(Icons.Filled.ShoppingCart, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add to Cart")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Product Detail View")
@Composable
private fun ProductDetailScreenPreview() {
    DreamyBloomAppTheme {
        ProductDetailScreen(navController = rememberNavController(), productId = 101)
    }
}