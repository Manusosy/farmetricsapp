package org.farmetricsapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    // Buttons, Cards, etc.
    small = RoundedCornerShape(8.dp),
    // Dialog boxes, Bottom sheets
    medium = RoundedCornerShape(12.dp),
    // Floating action buttons, Extended sheets
    large = RoundedCornerShape(16.dp)
)

// Custom shapes for specific components
val BottomSheetShape = RoundedCornerShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

val CardShape = RoundedCornerShape(16.dp)

val ButtonShape = RoundedCornerShape(12.dp)

val TextFieldShape = RoundedCornerShape(12.dp)

val ChipShape = RoundedCornerShape(24.dp) 