package app.loococo.presentation

import app.loococo.domain.model.StoreItem
import app.loococo.domain.usecase.FavoriteUseCase
import app.loococo.presentation.screen.favorite.FavoriteState
import app.loococo.presentation.screen.favorite.FavoriteViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.orbitmvi.orbit.test.test

class FavoriteViewModelTest {
    private val favoriteUseCase: FavoriteUseCase = mock()
    private lateinit var viewModel: FavoriteViewModel

    private val mockStoreItemList = listOf(
        StoreItem(
            code = "test1",
            thumbnailUrl = "test1",
            iconImageUrl = "test1",
            name = "test1",
            selected = false
        ),
        StoreItem(
            code = "test2",
            thumbnailUrl = "test2",
            iconImageUrl = "test2",
            name = "test2",
            selected = true
        )
    )

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(favoriteUseCase)
    }

    @Test
    fun `즐거찾기 리스트 로딩`() = runTest {
        whenever(favoriteUseCase.stores()).thenReturn(flowOf(mockStoreItemList))

        viewModel.test(this, FavoriteState()) {
            expectInitialState()
            runOnCreate().join()

            expectState(FavoriteState(stores = mockStoreItemList))

            cancelAndIgnoreRemainingItems()
        }
    }
}