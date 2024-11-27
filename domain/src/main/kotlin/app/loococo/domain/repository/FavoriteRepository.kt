package app.loococo.domain.repository

import app.loococo.domain.model.StoreItem
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(item: StoreItem)

    suspend fun delete(id: Long)

    fun stores(): Flow<List<StoreItem>>
}