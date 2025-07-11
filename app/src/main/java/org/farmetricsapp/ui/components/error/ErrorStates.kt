package org.farmetricsapp.ui.components.error

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import org.farmetricsapp.ui.theme.Dimensions

@Composable
fun FullScreenError(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Error
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        ErrorContent(
            message = message,
            onRetry = onRetry,
            icon = icon
        )
    }
}

@Composable
fun ErrorBanner(
    message: String,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    type: ErrorType = ErrorType.ERROR
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(
            animationSpec = tween(300, easing = LinearEasing)
        ) + fadeIn(
            animationSpec = tween(300, easing = LinearEasing)
        ),
        exit = shrinkVertically(
            animationSpec = tween(300, easing = LinearEasing)
        ) + fadeOut(
            animationSpec = tween(300, easing = LinearEasing)
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(type.backgroundColor)
                .padding(Dimensions.paddingMedium),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = type.icon,
                contentDescription = null,
                tint = type.contentColor
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = type.contentColor,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onDismiss,
                modifier = Modifier.alpha(0.7f)
            ) {
                Text("Dismiss")
            }
        }
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(300),
        label = "alpha"
    )

    Column(
        modifier = modifier
            .padding(Dimensions.paddingLarge)
            .alpha(alpha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(Dimensions.iconXLarge),
            tint = MaterialTheme.colorScheme.error
        )
        
        Spacer(modifier = Modifier.height(Dimensions.paddingLarge))
        
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(Dimensions.paddingLarge))
        
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

enum class ErrorType(
    val icon: ImageVector,
    val backgroundColor: androidx.compose.ui.graphics.Color,
    val contentColor: androidx.compose.ui.graphics.Color
) {
    ERROR(
        icon = Icons.Default.Error,
        backgroundColor = MaterialTheme.colorScheme.errorContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer
    ),
    WARNING(
        icon = Icons.Default.Warning,
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
    ),
    NETWORK(
        icon = Icons.Default.WifiOff,
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    )
} 