package app.loococo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Black
import app.loococo.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListTopBar(text: Int) {
    TopAppBar(
        modifier = Modifier,
        title = {
            ImageListTitleText(
                text = stringResource(text),
                color = White
            )
        },
        expandedHeight = 60.dp,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black
        )
    )
}

@Composable
fun ImageListBottomBar(content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        containerColor = White,
        tonalElevation = 1.dp,
        content = content
    )
}


@Composable
fun RowScope.ImageListNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    text: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clickable { onClick.invoke() }
            .background(if (selected) Black else White),
        contentAlignment = Alignment.Center
    ) {
        ImageListBodyText(
            text = stringResource(text),
            color = if (selected) White else Black
        )
    }
}