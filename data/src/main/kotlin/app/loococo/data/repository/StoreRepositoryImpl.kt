package app.loococo.data.repository

import app.loococo.data.remote.manger.StoreDataManger
import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import app.loococo.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val manger: StoreDataManger
) : StoreRepository {

    override suspend fun stores(): Flow<Resource<StoreData>> {
        return manger.stores()
    }
}