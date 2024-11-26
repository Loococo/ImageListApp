package app.loococo.presentation.screen.home

import app.loococo.domain.model.StoreItem

data class HomeState(
    val isLoading: Boolean = false,
    val stores: List<StoreItem> = emptyList()
)

sealed class HomeSideEffect {
    data class HomeTitle(val title: String) : HomeSideEffect()
    data class ShowToast(val res: String) : HomeSideEffect()
}

sealed class HomeEvent {
    data class OnFavoriteClicked(val item: StoreItem) : HomeEvent()
}