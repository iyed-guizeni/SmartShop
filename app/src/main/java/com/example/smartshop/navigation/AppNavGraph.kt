package com.example.smartshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartshop.authentication.LoginScreen
import com.example.smartshop.authentication.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val firebaseAuth = FirebaseAuth.getInstance()

    // Start destination (auto-login)
    val startDestination =
        if (firebaseAuth.currentUser != null) "home" else "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ================== LOGIN ==================
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        // ================== REGISTER ==================
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // ================== HOME (TEMP) ==================
        composable("home") {
            // TODO: HomeScreen()
        }
    }
}
