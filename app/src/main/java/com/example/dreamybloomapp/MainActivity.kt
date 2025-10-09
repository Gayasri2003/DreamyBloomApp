package com.example.dreamybloomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.dreamybloomapp.navigation.AppNavigation
import com.example.dreamybloomapp.ui.theme.DreamyBloomAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DreamyBloomAppTheme {
                AppNavigation()

            }
        }
    }
}
