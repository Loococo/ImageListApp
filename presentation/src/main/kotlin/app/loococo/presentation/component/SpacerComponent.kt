package app.loococo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WidthSpacer(
    width: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
) {
    Spacer(
        modifier = modifier
            .width(width.dp)
            .background(backgroundColor)
    )
}

@Composable
fun HeightSpacer(
    height: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
) {
    Spacer(
        modifier = modifier
            .height(height.dp)
            .background(backgroundColor)
    )
}