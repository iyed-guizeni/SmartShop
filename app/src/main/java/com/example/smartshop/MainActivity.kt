package com.example.smartshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.smartshop.navigation.AppNavGraph
import com.example.smartshop.ui.theme.SmartShopTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase once at app start
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()

        setContent {
            SmartShopTheme {
                // Launch the navigation graph (Splash → Login → Register → Home)
                AppNavGraph()
            }
        }
    }
}