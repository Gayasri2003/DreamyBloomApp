package com.example.dreamybloomapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dreamybloomapp.R
import com.example.dreamybloomapp.navigation.ScreenRoutes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme
import androidx.compose.foundation.layout.safeDrawingPadding

// --- Data Models
data class CartItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int,
    val imageRes: Int,
    val discount: Double = 0.00
)

// Dummy Cart Item Data
val cartItems = listOf(
    // Product 1:
    CartItem(
        id = 1,
        name = "RoseGlow Hydrating Lip Balm",
        description = "Natural Moisture & Shine | Vitamin E & Aloe Vera",
        price = 790.00,
        quantity = 2,
        imageRes = R.drawable.product_img_lipbalm,
        discount = 0.00
    ),
    // Product 2:
    CartItem(
        id = 2,
        name = "Deep Clean Face Wash",
        description = "Gentle Deep Clean | Removes Dirt & Oil | Green Tea",
        price = 1650.00,
        quantity = 1,
        imageRes = R.drawable.product_img_facewash,
        discount = 0.00
    ),
    // Product 3:
    CartItem(
        id = 3,
        name = "Daily Hydrating Face Cream",
        description = "Flawless Coverage | Lightweight Feel | Long-Lasting Wear",
        price = 1600.00,
        quantity = 1,
        imageRes = R.drawable.product_img_facilcream,
        discount = 350.00
    )
)

@Composable
fun CartScreen(navController: NavController) {
    val subtotal = cartItems.sumOf { it.price * it.quantity }
    val discount = cartItems.sumOf { it.discount }
    val shipping = 300.00
    val total = subtotal - discount + shipping

    // LazyColumn enables scrolling content under the fixed bottom bar
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

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

                // Cart Title (Pushed down by the Row's padding)
                Text(
                    text = "Cart",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f).padding(start = 16.dp)
                )

                // Placeholder Notification Icon
                IconButton(onClick = { /* Notifications */ }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.onBackground)
                }
            }

            // 1. Shipping Address Card
            AddressCard(
                title = "Shipping Address",
                address = "26, Duong So 2, Thao Dien Ward, An Phu, District 2, Ho Chi Minh city",
                onEditClick = { /* Edit address action */ }
            )

            // 2. Contact Information Card
            AddressCard(
                title = "Contact Information",
                address = "+84932000000\namandamorgan@example.com",
                onEditClick = { /* Edit contact action */ }
            )
        }

        // 3. Cart Items List
        items(cartItems) { item ->
            CartItemCard(item = item)
        }

        item {
            // 4. Order Summary
            OrderSummary(subtotal = subtotal, discount = discount, shipping = shipping, total = total)
        }

        item {
            // 5. Checkout Button
            Button(
                onClick = { /* Checkout action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Checkout", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// --- Component Composables ---

@Composable
fun AddressCard(title: String, address: String, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(address, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit $title", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            // Product Image with Delete Icon Overlay
            Box(contentAlignment = Alignment.TopStart) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                )
                // Delete Icon Button
                IconButton(
                    onClick = { /* Delete item action */ },
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.error, CircleShape)
                        .padding(2.dp)
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = MaterialTheme.colorScheme.onError)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceBetween) {
                // Name and Description
                Text(item.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(item.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2)

                Spacer(modifier = Modifier.height(8.dp))

                // Price and Quantity Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LKR ${String.format("%,.2f", item.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Quantity Controls
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        QuantityButton(Icons.Filled.Remove) { /* Decrease quantity */ }
                        Text("${item.quantity}", modifier = Modifier.padding(horizontal = 12.dp), fontWeight = FontWeight.SemiBold)
                        QuantityButton(Icons.Filled.Add) { /* Increase quantity */ }
                    }
                }
            }
        }
    }
}

@Composable
fun QuantityButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(32.dp),
        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer) // Use secondary for pink buttons
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
    }
}

@Composable
fun OrderSummary(subtotal: Double, discount: Double, shipping: Double, total: Double) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        // Items Total
        SummaryRow(label = "Items total", value = "LKR ${String.format("%,.2f", subtotal)}")
        // Discount
        SummaryRow(label = "Items discount", value = "- LKR ${String.format("%,.2f", discount)}", color = MaterialTheme.colorScheme.error)
        // Subtotal
        SummaryRow(label = "Subtotal", value = "LKR ${String.format("%,.2f", subtotal - discount)}", isTotal = true)
        // Shipping
        SummaryRow(label = "Shipping", value = "LKR ${String.format("%,.2f", shipping)}")

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Estimated Total
        SummaryRow(label = "Estimated total", value = "LKR ${String.format("%,.2f", total)}", isTotal = true)
    }
}

@Composable
fun SummaryRow(label: String, value: String, color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            value,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (isTotal) MaterialTheme.colorScheme.primary else color // Highlight total price
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenPreview() {
    // Wrap the screen in your custom theme for correct colors and typography
    DreamyBloomAppTheme {
        // Provide a mock NavController since the preview doesn't run real navigation
        CartScreen(navController = rememberNavController())
    }
}