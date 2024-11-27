package app.loococo.data.repository

import app.loococo.data.local.dao.StoreDao
import app.loococo.data.local.model.toStoreEntity
import app.loococo.data.local.model.toStoreItem
import app.loococo.domain.model.StoreItem
import app.loococo.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao
) : FavoriteRepository {

    override suspend fun insert(item: StoreItem) {
        storeDao.insert(item.toStoreEntity())
    }

    override suspend fun delete(id: Long) {
        storeDao.deleteById(id)
    }

    override fun stores(): Flow<List<StoreItem>> {
        return storeDao.getStores().map { stores -> stores.map { it.toStoreItem() } }
    }
}