package org.farmetricsapp.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

object FarmetricsAnimations {
    // Duration constants
    const val SHORT_DURATION = 150
    const val MEDIUM_DURATION = 300
    const val LONG_DURATION = 450

    // Easing
    val EaseInOut = FastOutSlowInEasing
    val EaseIn = FastOutLinearInEasing
    val EaseOut = LinearOutSlowInEasing
    
    // Animation specs
    val ButtonPress = tween<Float>(
        durationMillis = SHORT_DURATION,
        easing = EaseInOut
    )
    
    val ContentFade = tween<Float>(
        durationMillis = MEDIUM_DURATION,
        easing = EaseInOut
    )
    
    val NavigationTransition = tween<Float>(
        durationMillis = LONG_DURATION,
        easing = EaseInOut
    )
    
    val BottomSheetTransition = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
}

@Composable
fun rememberInfiniteTransition(
    label: String = "InfiniteTransition"
): InfiniteTransition = rememberInfiniteTransition(label = label)

// Pulse animation values
val PulseInitialScale = 0.95f
val PulsePeakScale = 1.05f
val PulseDuration = 1000

// Shimmer animation values
val ShimmerWidth = 100.dp
val ShimmerDuration = 1200
val ShimmerDelay = 300
val ShimmerColorShade = 0.1f 