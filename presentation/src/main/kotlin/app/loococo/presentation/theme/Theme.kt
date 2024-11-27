package app.loococo.presentation.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalMaterial3Api::class)
val rippleConfig = RippleConfiguration(
    color = White,
    rippleAlpha = RippleAlpha(
        draggedAlpha = 0.1f,
        focusedAlpha = 0.2f,
        hoveredAlpha = 0.15f,
        pressedAlpha = 0.25f
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        CompositionLocalProvider(
            LocalRippleConfiguration provides rippleConfig,
            content = content
        )
    }
}