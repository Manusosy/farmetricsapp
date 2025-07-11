package org.farmetricsapp.ui.components.buttons

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import org.farmetricsapp.ui.theme.Dimensions

@Composable
fun FarmetricsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        contentPadding = PaddingValues(
            horizontal = Dimensions.paddingLarge,
            vertical = Dimensions.paddingMedium
        )
    ) {
        ButtonContent(
            loading = loading,
            icon = icon,
            text = text
        )
    }
}

@Composable
fun FarmetricsOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        contentPadding = PaddingValues(
            horizontal = Dimensions.paddingLarge,
            vertical = Dimensions.paddingMedium
        )
    ) {
        ButtonContent(
            loading = loading,
            icon = icon,
            text = text
        )
    }
}

@Composable
fun FarmetricsTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    text: String
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        contentPadding = PaddingValues(
            horizontal = Dimensions.paddingLarge,
            vertical = Dimensions.paddingMedium
        )
    ) {
        ButtonContent(
            loading = loading,
            icon = icon,
            text = text
        )
    }
}

@Composable
fun FarmetricsElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    text: String
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        contentPadding = PaddingValues(
            horizontal = Dimensions.paddingLarge,
            vertical = Dimensions.paddingMedium
        )
    ) {
        ButtonContent(
            loading = loading,
            icon = icon,
            text = text
        )
    }
}

@Composable
fun FarmetricsTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
    text: String
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        contentPadding = PaddingValues(
            horizontal = Dimensions.paddingLarge,
            vertical = Dimensions.paddingMedium
        )
    ) {
        ButtonContent(
            loading = loading,
            icon = icon,
            text = text
        )
    }
}

@Composable
fun FarmetricsFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector = Icons.Default.Add,
    contentDescription: String? = null
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading
    ) {
        AnimatedContent(
            targetState = loading,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "loading"
        ) { isLoading ->
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(Dimensions.iconMedium),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            } else {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun ButtonContent(
    loading: Boolean,
    icon: ImageVector?,
    text: String,
    modifier: Modifier = Modifier
) {
    val alpha by animateFloatAsState(
        targetValue = if (loading) 0f else 1f,
        label = "alpha"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(Dimensions.iconMedium),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        
        Row(
            modifier = Modifier.alpha(alpha),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.iconMedium)
                )
                Spacer(modifier = Modifier.width(Dimensions.paddingSmall))
            }
            Text(text = text)
        }
    }
} 