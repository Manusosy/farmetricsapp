package org.farmetricsapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.farmetricsapp.presentation.main.HomeScreen
import org.farmetricsapp.presentation.main.ProfileScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    onLogout: () -> Unit
) {
    navigation(startDestination = "home", route = "main") {
        composable("home") {
            HomeScreen(
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }
        
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = onLogout
            )
        }
    }
} 