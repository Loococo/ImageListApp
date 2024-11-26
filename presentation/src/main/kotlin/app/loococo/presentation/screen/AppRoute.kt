package app.loococo.presentation.screen

import kotlinx.serialization.Serializable

sealed class AppRoute {

    @Serializable
    data object Home

    @Serializable
    data object Favorite

}