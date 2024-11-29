package app.loococo.presentation.screen.home

import androidx.lifecycle.ViewModel
import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreItem
import app.loococo.domain.usecase.FavoriteUseCase
import app.loococo.domain.usecase.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState()) { loadStoreData() }

    fun onEventReceived(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClicked -> toggleFavoriteStatus(event.item)
        }
    }

    private fun syncFavoriteStatus() = intent {
        favoriteUseCase.stores().collectLatest { favoriteItems ->
            val updatedStores = state.stores.map { store ->
                store.copy(
                    selected = favoriteItems.any { it.code == store.code },
                    id = favoriteItems.find { it.code == store.code }?.id ?: store.id
                )
            }
            reduce { state.copy(stores = updatedStores) }
        }
    }

    private fun toggleFavoriteStatus(item: StoreItem) = intent {
        val updatedStores = state.stores.map {
            if (it.code == item.code) {
                val newSelectedStatus = !it.selected
                it.copy(selected = newSelectedStatus)
            } else it
        }
        reduce { state.copy(stores = updatedStores) }

        val action = if (!item.selected) favoriteUseCase::insert else favoriteUseCase::delete
        action(item)
    }

    private fun loadStoreData() = intent {
        storeUseCase().collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    reduce { state.copy(isLoading = false, stores = result.data.stores) }
                    postSideEffect(HomeSideEffect.HomeTitle(result.data.title))
                    syncFavoriteStatus()
                }

                is Resource.Error -> {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(HomeSideEffect.ShowToast(result.error))
                }

                Resource.Loading -> {
                    reduce { state.copy(isLoading = true) }
                }
            }
        }
    }
}