package app.loococo.presentation

import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import app.loococo.domain.model.StoreItem
import app.loococo.domain.repository.StoreRepository
import app.loococo.domain.usecase.StoreUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class StoreUseCaseTest {

    private val storeRepository: StoreRepository = mock()
    private lateinit var storeUseCase: StoreUseCase

    private val mockStoreData = StoreData(
        title = "test",
        stores = listOf(
            StoreItem(
                code = "test1",
                thumbnailUrl = "test1",
                iconImageUrl = "test1",
                name = "test1",
            ),
            StoreItem(
                code = "test2",
                thumbnailUrl = "test2",
                iconImageUrl = "test2",
                name = "test2",
            )
        )
    )

    @Before
    fun setup() {
        storeUseCase = StoreUseCase(storeRepository)
    }

    @Test
    fun `데이터 로드 성공`() = runTest {
        whenever(storeRepository.stores()).thenReturn(flow {
            emit(Resource.Loading)
            emit(Resource.Success(mockStoreData))
        })

        val result = storeUseCase().toList()

        assertEquals(2, result.size)
        assertEquals(Resource.Loading, result[0])
        assertEquals(Resource.Success(mockStoreData), result[1])
    }

    @Test
    fun `데이터 로드 실패`() = runTest {
        val errorMessage = "Test Error"
        whenever(storeRepository.stores()).thenReturn(flow {
            emit(Resource.Loading)
            emit(Resource.Error(errorMessage))
        })

        val result = storeUseCase().toList()

        assertEquals(2, result.size)
        assertEquals(Resource.Loading, result[0])
        assertEquals(Resource.Error(errorMessage), result[1])
    }

    @Test
    fun `빈 데이터 로드`() = runTest {
        val emptyStoreData = StoreData(title = "", stores = emptyList())
        whenever(storeRepository.stores()).thenReturn(flow {
            emit(Resource.Loading)
            emit(Resource.Success(emptyStoreData))
        })

        val result = storeUseCase().toList()

        assertEquals(2, result.size)
        assertEquals(Resource.Loading, result[0])
        assertEquals(Resource.Success(emptyStoreData), result[1])
    }
}