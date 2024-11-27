package app.loococo.presentation.screen.favorite

import app.loococo.domain.model.StoreItem

data class FavoriteState(
    val stores: List<StoreItem> = emptyList()
)

sealed class FavoriteSideEffect {
}

sealed class FavoriteEvent {
    data class OnFavoriteClicked(val item: StoreItem) : FavoriteEvent()
}