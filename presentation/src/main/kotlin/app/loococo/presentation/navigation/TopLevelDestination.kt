package app.loococo.presentation.navigation

import androidx.annotation.StringRes
import app.loococo.presentation.R
import app.loococo.presentation.screen.AppRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    @StringRes val titleTextId:Int,
    @StringRes val contentTextId: Int,
    val route: KClass<*>
) {
    HOME(
        titleTextId = R.string.home_title,
        contentTextId = R.string.home,
        route = AppRoute.Home::class
    ),
    Favorite(
        titleTextId = R.string.favorite,
        contentTextId = R.string.favorite,
        route = AppRoute.Favorite::class
    )
}