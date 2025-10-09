package com.example.dreamybloomapp

import com.example.dreamybloomapp.R
import androidx.compose.runtime.Composable // Used for accessing R.drawable

// --- SHARED DATA MODELS (DEFINED ONCE) ---
// Note: Category is included for filtering/organization
data class Product(val id: Int, val name: String, val price: String, val imageRes: Int, val category: String = "General")
data class SkinType(val id: Int, val name: String, val imageRes: Int)

// --- SHARED DUMMY DATA LISTS (DEFINED ONCE) ---

// Data for Home Screen
val newArrivals = listOf(
    Product(1, "Lip Balm", "$12.99", R.drawable.product_img_lipbalm, "Skincare"),
    Product(2, "Face Wash", "$18.50", R.drawable.product_img_facewash, "Skincare"),
    Product(3, "Body Milk", "$25.00", R.drawable.product_img_bodymilk, "Body Care"),
    Product(4, "Lotion Oil", "$30.75", R.drawable.product_img_lotionoil, "Body Care"),
    Product(5, "Toner Mist", "$15.90", R.drawable.product_img_toner, "Skincare"),
    Product(6, "Face Serum", "$45.00", R.drawable.product_img_facecerum, "Skincare"),
)
val offers = listOf("20% Off Skincare", "Free Shipping Over $50", "Buy 2 Get 1 Free", "Seasonal Sale")
val skinTypes = listOf(
    SkinType(1, "Oily Skin", R.drawable.skintype_oilyskin),
    SkinType(2, "Dry Skin", R.drawable.skintype_dryskin),
    SkinType(3, "Sensitive Skin", R.drawable.skintype_sensitiveskin),
)
val mostPurchased = listOf(
    Product(10, "Daily Cream", "$22.00", R.drawable.product_img_dailycream, "Skincare"),
    Product(11, "Face Cream", "$35.00", R.drawable.product_img_facilcream, "Skincare"),
    Product(12, "Oil Serum", "$48.00", R.drawable.product_img_oilserum, "Skincare"),
    Product(13, "Eye Shadow", "$15.00", R.drawable.product_img_eyeshadow, "Makeup"),
    Product(14, "Mascara", "$18.00", R.drawable.product_img_mascara, "Makeup"),
    Product(15, "Lipstick", "$20.00", R.drawable.product_img_lipstick, "Makeup"),
)

// Data for Product Screen sections (unique to ProductScreen, but placed here for organization)
val productData = mapOf(
    "Anti-Aging Solutions" to listOf(
        Product(101, "Acne Face Wash", "1,500.00", R.drawable.product_acne_facewash, "Skincare"),
        Product(104, "Hydration Lotion", "2,300.00", R.drawable.product_hydration_lotion, "Skincare"),
        Product(107, "Body Butter", "1,900.00", R.drawable.product_body_butter, "Body Care"),
        Product(108, "Radiance Tablets", "3,500.00", R.drawable.product_radiance_tablets, "Wellness"),
    ),
    "Acne & Blemish Control Solutions" to listOf(
        Product(201, "Pimple Clear", "1,100.00", R.drawable.product_pimple_clear, "Skincare"),
        Product(203, "Face Protection", "2,500.00", R.drawable.product_face_protection, "Skincare"),
        Product(204, "Sun Cream", "1,950.00", R.drawable.product_sun_cream, "Skincare"),
        Product(206, "Moisturizer", "2,700.00", R.drawable.product_moisturizer, "Skincare"),
    ),
    "Hair Care Essentials" to listOf(
        Product(301, "Scalp Shampoo", "1,400.00", R.drawable.product_hair_shampoo, "Hair Care"),
        Product(302, "Deep Conditioner", "1,600.00", R.drawable.product_hair_conditioner, "Hair Care"),
        Product(303, "Hair Oil", "2,100.00", R.drawable.product_hair_oil, "Hair Care"),
        Product(304, "Hair Mask", "2,900.00", R.drawable.product_hair_mask, "Hair Care"),
    ),
    "Makeup Must-Haves" to listOf(
        Product(401, "Liquid Foundation", "3,200.00", R.drawable.product_foundation, "Makeup"),
        Product(402, "Matte Lipstick", "1,700.00", R.drawable.product_lipstick, "Makeup"),
        Product(403, "Eyeliner Pen", "1,350.00", R.drawable.product_eyeliner, "Makeup"),
        Product(404, "Blush Palette", "2,000.00", R.drawable.product_blush, "Makeup"),
    ),
    "Body Care Daily" to listOf(
        Product(501, "Hand Cream", "950.00", R.drawable.product_handcream, "Body Care"),
        Product(502, "Foot Scrub", "1,100.00", R.drawable.product_scrub, "Body Care"),
        Product(503, "Body Spray", "1,600.00", R.drawable.product_bodyspray, "Body Care"),
        Product(504, "Aroma Oil", "2,800.00", R.drawable.product_aromaoil, "Wellness"),
    )
)