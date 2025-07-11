package org.farmetricsapp.presentation.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.farmetricsapp.ui.theme.FarmetricsAnimations

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
    object ForgotPassword : AuthScreen("forgot_password")
}

@Composable
fun AuthNavigation(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            route = AuthScreen.Login.route,
            enterTransition = {
                when (initialState.destination.route) {
                    AuthScreen.Register.route -> slideInHorizontally(
                        animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                        initialOffsetX = { -it }
                    ) + fadeIn(tween(FarmetricsAnimations.LONG_DURATION))
                    AuthScreen.ForgotPassword.route -> slideInHorizontally(
                        animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                        initialOffsetX = { -it }
                    ) + fadeIn(tween(FarmetricsAnimations.LONG_DURATION))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    AuthScreen.Register.route -> slideOutHorizontally(
                        animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                        targetOffsetX = { it }
                    ) + fadeOut(tween(FarmetricsAnimations.LONG_DURATION))
                    AuthScreen.ForgotPassword.route -> slideOutHorizontally(
                        animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                        targetOffsetX = { it }
                    ) + fadeOut(tween(FarmetricsAnimations.LONG_DURATION))
                    else -> null
                }
            }
        ) {
            LoginScreen(
                viewModel = viewModel,
                onNavigateToRegister = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(AuthScreen.ForgotPassword.route)
                },
                onLoginSuccess = onAuthSuccess
            )
        }
        
        composable(
            route = AuthScreen.Register.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                    initialOffsetX = { it }
                ) + fadeIn(tween(FarmetricsAnimations.LONG_DURATION))
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                    targetOffsetX = { -it }
                ) + fadeOut(tween(FarmetricsAnimations.LONG_DURATION))
            }
        ) {
            RegisterScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Login.route) { inclusive = true }
                    }
                },
                onRegisterSuccess = onAuthSuccess
            )
        }
        
        composable(
            route = AuthScreen.ForgotPassword.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                    initialOffsetX = { it }
                ) + fadeIn(tween(FarmetricsAnimations.LONG_DURATION))
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(FarmetricsAnimations.LONG_DURATION),
                    targetOffsetX = { -it }
                ) + fadeOut(tween(FarmetricsAnimations.LONG_DURATION))
            }
        ) {
            ForgotPasswordScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
} 