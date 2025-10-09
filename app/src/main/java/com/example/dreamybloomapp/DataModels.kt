package com.example.dreamybloomapp

import com.example.dreamybloomapp.R
import androidx.compose.runtime.Composable // Used for accessing R.drawable

// --- DATA MODELS  ---
data class Product(val id: Int, val name: String, val price: String, val imageRes: Int, val category: String = "General", val description: String)
data class SkinType(val id: Int, val name: String, val imageRes: Int)

// --- SHARED DUMMY DATA LISTS (DEFINED ONCE) ---

val defaultDesc = "Experience the natural glow, long-lasting wear, and flawless coverage for your skin."
// Data for Home Screen
val newArrivals = listOf(
    Product(1, "Lip Balm", "790", R.drawable.product_img_lipbalm, "Skincare", "Moisturizing formula with SPF 15 and deep cherry flavor."),
    Product(2, "Face Wash", "1650", R.drawable.product_img_facewash, "Skincare", "Gentle foaming cleanser removing dirt and excess oil."),
    Product(3, "Body Milk", "3200", R.drawable.product_img_bodymilk, "Body Care", "Luxurious, non-greasy body milk for 24-hour hydration."),
    Product(4, "Lotion Oil", "2850", R.drawable.product_img_lotionoil, "Body Care", "Deeply nourishing oil blend to enhance skin's natural barrier."),
    Product(5, "Toner Mist", "1250", R.drawable.product_img_toner, "Skincare", "Refreshing mist to balance pH and minimize pores."),
    Product(6, "Face Serum", "1380", R.drawable.product_img_facecerum, "Skincare", "Potent serum for targeted repair and intense revitalization."),
)
val offers = listOf("20% Off Skincare", "Free Shipping Over $50", "Buy 2 Get 1 Free", "Seasonal Sale")
val skinTypes = listOf(
    SkinType(7, "Oily Skin", R.drawable.skintype_oilyskin),
    SkinType(8, "Dry Skin", R.drawable.skintype_dryskin),
    SkinType(9, "Sensitive Skin", R.drawable.skintype_sensitiveskin),
)
val mostPurchased = listOf(
    Product(10, "Daily Cream", "1300.00", R.drawable.product_img_dailycream, "Skincare", "Daily non-comedogenic cream for foundational hydration."),
    Product(11, "Face Cream", "1600.00", R.drawable.product_img_facilcream, "Skincare", "Rich cream blend to combat dryness and improve texture."),
    Product(12, "Oil Serum", "2800.00", R.drawable.product_img_oilserum, "Skincare", "Lightweight oil to balance sebum production and reduce redness."),
    Product(13, "Eye Shadow", "1000.00", R.drawable.product_img_eyeshadow, "Makeup", "Highly pigmented shadow for dramatic, long-lasting color."),
    Product(14, "Mascara", "980.00", R.drawable.product_img_mascara, "Makeup", "Volumizing mascara for dark, full, and separated lashes."),
    Product(15, "Lipstick", "820.00", R.drawable.product_img_lipstick, "Makeup", "Classic matte lipstick offering rich color and comfortable wear."),
)

// Data for Product Screen sections (unique to ProductScreen)
val productData = mapOf(
    "Anti-Aging Solutions" to listOf(
        Product(101, "Acne Face Wash", "1,500.00", R.drawable.product_acne_facewash, "Skincare", "Targeted wash to reduce breakouts and minimize signs of aging."),
        Product(104, "Hydration Lotion", "2,300.00", R.drawable.product_hydration_lotion, "Skincare", "Intensive lotion that locks in moisture and plumps skin."),
        Product(107, "Body Butter", "1,900.00", R.drawable.product_body_butter, "Body Care", "Thick, creamy butter that restores elasticity to dry skin."),
        Product(108, "Radiance Tablets", "3,500.00", R.drawable.product_radiance_tablets, "Wellness", "Daily supplement to promote inner glow and clear complexion."),
    ),
    "Acne & Blemish Control Solutions" to listOf(
        Product(201, "Pimple Clear", "1,100.00", R.drawable.product_pimple_clear, "Skincare", "Spot treatment with salicylic acid to quickly reduce inflammation."),
        Product(203, "Face Protection", "2,500.00", R.drawable.product_face_protection, "Skincare", "Broad-spectrum SPF 50 sunscreen for complete UV defense."),
        Product(204, "Sun Cream", "1,950.00", R.drawable.product_sun_cream, "Skincare", "Lightweight, non-greasy sun cream for daily protection."),
        Product(206, "Moisturizer", "2,700.00", R.drawable.product_moisturizer, "Skincare", "Oil-free moisturizer for clear, hydrated, and calm skin."),
    ),
    "Hair Care Essentials" to listOf(
        Product(301, "Scalp Shampoo", "1,400.00", R.drawable.product_hair_shampoo, "Hair Care", "Gentle shampoo to soothe and clarify the scalp, removing buildup."),
        Product(302, "Deep Conditioner", "1,600.00", R.drawable.product_hair_conditioner, "Hair Care", "Restorative conditioner for brittle, damaged, and over-processed hair."),
        Product(303, "Hair Oil", "2,100.00", R.drawable.product_hair_oil, "Hair Care", "Shine-enhancing oil that reduces frizz and breakage."),
        Product(304, "Hair Mask", "2,900.00", R.drawable.product_hair_mask, "Hair Care", "Intensive weekly mask for deep repair and moisture."),
    ),
    "Makeup Must-Haves" to listOf(
        Product(401, "Liquid Foundation", "3,200.00", R.drawable.product_foundation, "Makeup", "Full coverage foundation with a natural, satin finish."),
        Product(402, "Matte Lipstick", "1,700.00", R.drawable.product_lipstick, "Makeup", "Comfortable matte formula in rich, classic colors."),
        Product(403, "Eyeliner Pen", "1,350.00", R.drawable.product_eyeliner, "Makeup", "Precision eyeliner with a waterproof and smudge-proof formula."),
        Product(404, "Blush Palette", "2,000.00", R.drawable.product_blush, "Makeup", "Curated palette for contouring, blushing, and highlighting."),
    ),
    "Body Care Daily" to listOf(
        Product(501, "Hand Cream", "950.00", R.drawable.product_handcream, "Body Care", "Fast-absorbing cream to protect and soften dry, cracked hands."),
        Product(502, "Foot Scrub", "1,100.00", R.drawable.product_scrub, "Body Care", "Exfoliating scrub to smooth rough patches and revitalize feet."),
        Product(503, "Body Spray", "1,600.00", R.drawable.product_bodyspray, "Body Care", "Lightly scented body spray for a refreshing daily lift."),
        Product(504, "Aroma Oil", "2,800.00", R.drawable.product_aromaoil, "Wellness", "Relaxing oil blend to soothe muscles and promote restful sleep."),
    )
)