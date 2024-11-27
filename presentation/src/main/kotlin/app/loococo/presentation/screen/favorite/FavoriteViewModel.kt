package app.loococo.presentation.screen.favorite

import androidx.lifecycle.ViewModel
import app.loococo.domain.model.StoreItem
import app.loococo.domain.usecase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ContainerHost<FavoriteState, FavoriteSideEffect>, ViewModel() {

    override val container = container<FavoriteState, FavoriteSideEffect>(FavoriteState())

    init {
        loadStoreData()
    }

    fun onEventReceived(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnFavoriteClicked -> onFavoriteClicked(event.item)
        }
    }

    private fun onFavoriteClicked(item: StoreItem) = intent {
        favoriteUseCase.delete(item)
    }

    private fun loadStoreData() = intent {
        favoriteUseCase.stores().collectLatest { favoriteItems ->
            reduce { state.copy(stores = favoriteItems) }
        }
    }
}