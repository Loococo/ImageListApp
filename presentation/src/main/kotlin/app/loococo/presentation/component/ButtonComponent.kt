package app.loococo.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Black
import app.loococo.presentation.theme.Orange
import app.loococo.presentation.utils.ImageListIcons

@Composable
fun ImageListIconButton(
    size: Dp,
    icon: ImageVector,
    description: String,
    color: Color = Black,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.size(size),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(size),
            tint = color
        )
    }
}

@Composable
fun ImageListIconButton(
    selected: Boolean,
    onClick: () -> Unit
) {
    ImageListIconButton(
        size = 35.dp,
        icon = if (selected) ImageListIcons.Favorite else ImageListIcons.FavoriteBorder,
        description = "imageListIconButton",
        color = Orange,
        onClick = onClick
    )
}