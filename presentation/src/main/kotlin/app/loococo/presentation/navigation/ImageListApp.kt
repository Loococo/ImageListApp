package app.loococo.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.loococo.presentation.component.ImageListBottomBar
import app.loococo.presentation.component.ImageListNavigationBarItem
import app.loococo.presentation.component.ImageListTopBar
import app.loococo.presentation.theme.ImageListTheme

@Composable
fun ImageListApp(appState: ImageListAppState = rememberImageListAppState()) {

    ImageListTheme {
        Scaffold(
            topBar = {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    ImageListTopBar(text = destination.titleTextId)
                }
            },
            bottomBar = {
                ImageListBottomBar {
                    appState.topLevelDestinations.forEach { destination ->
                        ImageListNavigationBarItem(
                            selected = appState.currentDestination.isRouteInHierarchy(destination.route),
                            onClick = { appState.navigateToTopLevelDestination(destination) },
                            text = destination.contentTextId
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ImageListNavHost(appState)
            }
        }
    }
}