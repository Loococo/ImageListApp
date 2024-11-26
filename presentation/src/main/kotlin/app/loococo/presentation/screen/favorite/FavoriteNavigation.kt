package app.loococo.presentation.screen.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.loococo.presentation.screen.AppRoute

fun NavGraphBuilder.favoriteScreen() {
    composable<AppRoute.Favorite> {
        FavoriteRoute()
    }
}

fun NavController.navigateToFavorite(navOptions: NavOptions) {
    this.navigate(AppRoute.Favorite, navOptions)
}