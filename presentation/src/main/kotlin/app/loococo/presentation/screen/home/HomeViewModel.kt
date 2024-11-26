package app.loococo.presentation.screen.home

import androidx.lifecycle.ViewModel
import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreItem
import app.loococo.domain.usecase.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: StoreUseCase) :
    ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    init {
        loadStore()
    }

    fun onEventReceived(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClicked -> onFavoriteClicked(event.item)
        }
    }

    private fun onFavoriteClicked(item: StoreItem) = intent {
        val updatedStores = state.stores.map {
            if (it.code == item.code) it.copy(selected = !it.selected) else it
        }
        reduce { state.copy(stores = updatedStores) }
    }

    private fun loadStore() = intent {
        useCase().collectLatest {
            when (it) {
                is Resource.Success -> {
                    reduce { state.copy(isLoading = false, stores = it.data.stores) }
                    postSideEffect(HomeSideEffect.HomeTitle(it.data.title))
                }

                is Resource.Error -> {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(HomeSideEffect.ShowToast(it.error))
                }

                Resource.Loading -> {
                    reduce { state.copy(isLoading = true) }
                }
            }
        }
    }
}