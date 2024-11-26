package app.loococo.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import app.loococo.presentation.R
import app.loococo.presentation.utils.ImageListIcons
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageListAsyncImage(image: String) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun ImageListAsyncCircleImage(modifier: Modifier, image: String) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image)
            .build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape),
        error = rememberVectorPainter(image = ImageListIcons.Warning),
    )
}