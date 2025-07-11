package org.farmetricsapp.ui.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

object ScreenTransitions {
    private const val ANIMATION_DURATION = 300
    private const val FADE_DURATION = 200
    
    // Horizontal slide with fade for main navigation
    fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            initialOffsetX = { fullWidth -> fullWidth }
        ) + fadeIn(animationSpec = tween(FADE_DURATION))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { fullWidth -> -fullWidth }
        ) + fadeOut(animationSpec = tween(FADE_DURATION))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            initialOffsetX = { fullWidth -> -fullWidth }
        ) + fadeIn(animationSpec = tween(FADE_DURATION))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { fullWidth -> fullWidth }
        ) + fadeOut(animationSpec = tween(FADE_DURATION))
    }

    // Vertical slide with fade for modal-like screens
    fun AnimatedContentTransitionScope<NavBackStackEntry>.enterModalTransition(): EnterTransition {
        return slideInVertically(
            animationSpec = tween(ANIMATION_DURATION),
            initialOffsetY = { fullHeight -> fullHeight }
        ) + fadeIn(animationSpec = tween(FADE_DURATION))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.exitModalTransition(): ExitTransition {
        return slideOutVertically(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetY = { fullHeight -> fullHeight }
        ) + fadeOut(animationSpec = tween(FADE_DURATION))
    }

    // Scale with fade for detail screens
    fun AnimatedContentTransitionScope<NavBackStackEntry>.enterDetailTransition(): EnterTransition {
        return scaleIn(
            animationSpec = tween(ANIMATION_DURATION),
            initialScale = 0.8f
        ) + fadeIn(animationSpec = tween(FADE_DURATION))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.exitDetailTransition(): ExitTransition {
        return scaleOut(
            animationSpec = tween(ANIMATION_DURATION),
            targetScale = 0.8f
        ) + fadeOut(animationSpec = tween(FADE_DURATION))
    }
} 