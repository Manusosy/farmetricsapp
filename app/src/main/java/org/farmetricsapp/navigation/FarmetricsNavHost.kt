package org.farmetricsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.farmetricsapp.presentation.auth.AuthViewModel

@Composable
fun FarmetricsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    startDestination: String = "auth"
) {
    val authState by authViewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Auth navigation graph
        authNavGraph(
            navController = navController,
            onAuthenticated = {
                navController.navigate("main") {
                    popUpTo("auth") { inclusive = true }
                }
            }
        )

        // Main navigation graph
        mainNavGraph(
            navController = navController,
            onLogout = {
                authViewModel.signOut()
                navController.navigate("auth") {
                    popUpTo("main") { inclusive = true }
                }
            }
        )
    }
} 