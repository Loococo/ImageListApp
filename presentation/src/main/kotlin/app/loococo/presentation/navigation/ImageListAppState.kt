package app.loococo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.loococo.presentation.screen.favorite.navigateToFavorite
import app.loococo.presentation.screen.home.navigateToHome
import kotlin.reflect.KClass

@Composable
fun rememberImageListAppState(
    navController: NavHostController = rememberNavController(),
): ImageListAppState {
    return remember(navController) {
        ImageListAppState(
            navController = navController
        )
    }
}

@Stable
class ImageListAppState(val navController: NavHostController) {
    var currentTitle: String by mutableStateOf("")
        private set

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    private val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) ?: false
            }
        }

    fun updateTitle(newTitle: String) {
        currentTitle = newTitle
    }

    @Composable
    fun updateTitleFromTopLevelDestination() {
        currentTopLevelDestination?.let { destination ->
            currentTitle = when (destination) {
                TopLevelDestination.HOME ->
                    currentTitle.ifBlank { stringResource(destination.titleTextId) }

                else -> stringResource(destination.titleTextId)
            }
        }
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.Favorite -> navController.navigateToFavorite(topLevelNavOptions)
            }
        }
    }
}

fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false