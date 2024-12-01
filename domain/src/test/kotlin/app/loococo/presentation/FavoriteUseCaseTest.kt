package app.loococo.presentation

import app.loococo.domain.model.StoreItem
import app.loococo.domain.repository.FavoriteRepository
import app.loococo.domain.usecase.FavoriteUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FavoriteUseCaseTest {

    private val favoriteRepository: FavoriteRepository = mock()
    private lateinit var favoriteUseCase: FavoriteUseCase

    private val mockStoreItem = StoreItem(
        id = 1L,
        code = "test1",
        thumbnailUrl = "test1",
        iconImageUrl = "test1",
        name = "test1",
    )

    @Before
    fun setup() {
        favoriteUseCase = FavoriteUseCase(favoriteRepository)
    }

    @Test
    fun `데이터 추가`() = runTest {
        favoriteUseCase.insert(mockStoreItem)

        verify(favoriteRepository).insert(mockStoreItem)
    }

    @Test
    fun `데이터 삭제`() = runTest {
        favoriteUseCase.delete(mockStoreItem)

        verify(favoriteRepository).delete(mockStoreItem.id)
    }

    @Test
    fun `데이터 리스트 로드`() = runTest {
        whenever(favoriteRepository.stores()).thenReturn(flowOf(listOf(mockStoreItem)))

        val result = favoriteUseCase.stores().toList()

        assertTrue(result.isNotEmpty())
        assertEquals(listOf(mockStoreItem), result[0])
    }

    @Test
    fun `빈 데이터 리스트 로드`() = runTest {
        whenever(favoriteRepository.stores()).thenReturn(flowOf(emptyList()))

        val result = favoriteUseCase.stores().toList()

        assertTrue(result[0].isEmpty())
    }

    @Test
    fun `중복 데이터 추가 방지`() = runTest {
        val duplicateStoreItem = mockStoreItem.copy()

        favoriteUseCase.insert(duplicateStoreItem)

        verify(favoriteRepository).insert(duplicateStoreItem)
    }
}