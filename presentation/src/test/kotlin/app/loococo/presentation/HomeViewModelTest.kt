package app.loococo.presentation

import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import app.loococo.domain.model.StoreItem
import app.loococo.domain.usecase.FavoriteUseCase
import app.loococo.domain.usecase.StoreUseCase
import app.loococo.presentation.screen.home.HomeEvent
import app.loococo.presentation.screen.home.HomeSideEffect
import app.loococo.presentation.screen.home.HomeState
import app.loococo.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.orbitmvi.orbit.test.test

class HomeViewModelTest {

    private val storeUseCase: StoreUseCase = mock()
    private val favoriteUseCase: FavoriteUseCase = mock()

    private lateinit var viewModel: HomeViewModel

    private val mockStoreData = StoreData(
        title = "test",
        stores = listOf(
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
    )

    @Before
    fun setup() {
        viewModel = HomeViewModel(storeUseCase, favoriteUseCase)
    }

    @Test
    fun `데이터 로딩 성공`() = runTest {
        whenever(storeUseCase()).thenReturn(flowOf(Resource.Success(mockStoreData)))

        viewModel.test(this, HomeState()) {
            expectInitialState()
            runOnCreate().join()

            expectState(HomeState(isLoading = false, stores = mockStoreData.stores))

            expectSideEffect(HomeSideEffect.HomeTitle(mockStoreData.title))

            cancelAndIgnoreRemainingItems()
        }
    }

    @Test
    fun `데이터 로딩 실패`() = runTest {
        whenever(storeUseCase()).thenReturn(flowOf(Resource.Error("error")))

        viewModel.test(this, HomeState()) {
            expectInitialState()
            runOnCreate().join()

            expectSideEffect(HomeSideEffect.ShowToast("error"))

            cancelAndIgnoreRemainingItems()
        }
    }

    @Test
    fun `즐겨찾기 추가`() = runTest {
        val storeItem = StoreItem(
            code = "test1",
            thumbnailUrl = "test1",
            iconImageUrl = "test1",
            name = "test1",
            selected = false
        )

        whenever(favoriteUseCase.insert(storeItem)).thenReturn(Unit)

        viewModel.test(this, HomeState(stores = listOf(storeItem))) {
            expectInitialState()

            viewModel.onEventReceived(HomeEvent.OnFavoriteClicked(storeItem))

            expectState(HomeState(stores = listOf(storeItem.copy(selected = true))))

            verify(favoriteUseCase).insert(storeItem)
        }
    }

    @Test
    fun `즐겨찾기 삭제`() = runTest {
        val storeItem = StoreItem(
            code = "test1",
            thumbnailUrl = "test1",
            iconImageUrl = "test1",
            name = "test1",
            selected = true
        )

        whenever(favoriteUseCase.delete(storeItem)).thenReturn(Unit)

        viewModel.test(this, HomeState(stores = listOf(storeItem))) {
            expectInitialState()

            viewModel.onEventReceived(HomeEvent.OnFavoriteClicked(storeItem))

            expectState(HomeState(stores = listOf(storeItem.copy(selected = false))))

            verify(favoriteUseCase).delete(storeItem)
        }
    }
}