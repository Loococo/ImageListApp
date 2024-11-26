package app.loococo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.loococo.presentation.screen.AppRoute
import app.loococo.presentation.screen.favorite.favoriteScreen
import app.loococo.presentation.screen.home.homeScreen

@Composable
fun ImageListNavHost(appState: ImageListAppState) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = AppRoute.Home
    ) {
        homeScreen()
        favoriteScreen()
    }
}