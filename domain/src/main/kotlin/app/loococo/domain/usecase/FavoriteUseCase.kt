package app.loococo.domain.usecase

import app.loococo.domain.model.StoreItem
import app.loococo.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun insert(item: StoreItem) {
        favoriteRepository.insert(item)
    }

    suspend fun delete(item: StoreItem) {
        favoriteRepository.delete(item.id)
    }

    fun stores(): Flow<List<StoreItem>> {
        return favoriteRepository.stores()
    }
}