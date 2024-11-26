package app.loococo.domain.repository

import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun stores(): Flow<Resource<StoreData>>
}