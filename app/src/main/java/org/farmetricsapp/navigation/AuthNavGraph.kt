package org.farmetricsapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.farmetricsapp.presentation.auth.LoginScreen
import org.farmetricsapp.presentation.auth.RegisterScreen
import org.farmetricsapp.presentation.auth.ForgotPasswordScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onAuthenticated: () -> Unit
) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") },
                onLoginSuccess = onAuthenticated
            )
        }
        
        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = onAuthenticated
            )
        }
        
        composable("forgot_password") {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
} 