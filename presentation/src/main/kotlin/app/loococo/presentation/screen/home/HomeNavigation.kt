package app.loococo.presentation.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.loococo.presentation.screen.AppRoute

fun NavGraphBuilder.homeScreen(onHomeTitle: (String) -> Unit) {
    composable<AppRoute.Home> {
        HomeRoute(onHomeTitle)
    }
}

fun NavController.navigateToHome(navOptions: NavOptions) {
    this.navigate(AppRoute.Home, navOptions)
}